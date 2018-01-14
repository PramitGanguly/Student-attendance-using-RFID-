package com.SohojWeb.DSI.UHF_Reader.CardManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import javax.xml.bind.DatatypeConverter;

import com.SohojWeb.DSI.UHF_Reader.JNI.UHF_Reader_JNI;

public class CardManager {
	private UHF_Reader_JNI reader;
	private PriorityQueue<String> studentQueue;
	private HashSet<String> returnList;
	private String ip;
	private int port;
	
	private Thread threadScanner;
	private Thread threadTimer;

	public CardManager(String ipaddr, int portaddr) {
		this.ip = ipaddr;
		this.port = portaddr;
		
		reader = new UHF_Reader_JNI();
		studentQueue = new PriorityQueue<>();
		returnList = new HashSet<>();
		
		threadScanner = new Thread(getScannerRunn());
		threadScanner.setName("ID Scanner Thread");
		threadScanner.setDaemon(true);
		threadTimer = new Thread(getTimerRunn());
		threadTimer.setName("Quere Refresh Thread");
		threadTimer.setDaemon(true);
		
		threadScanner.start();
		threadTimer.start();
	}
	
	private Runnable getScannerRunn() {
		return new Runnable() {

			@Override
			public void run() {
				String tmp;
				String tmp2;
				ArrayList<String> cardList;
				while(true) {
					tmp = null;
					tmp = reader.Inventory_G2_EPC_TCPIP(ip, port);
					if(tmp != null) {
						char[] chars = tmp.toCharArray();
						if(chars.length >= 3) {
							if(!((byte)chars[0] == 'd' && (byte)chars[1] == 'i' && (byte)chars[2] == 'd')) {
								tmp2 = null;
								tmp2 = reader.Inventory_G2_TCPIP(ip, port);
								if(tmp2 != null) {
									cardList = ConvertStringToArrayList(tmp2);
									synchronized(studentQueue) {
										for(String card : cardList) {
											if(!studentQueue.contains(card)) {
												studentQueue.add(card);
											}
										}
									}
									cardList.clear();
								}
							}
						}
					}
				}
			}
			
			private ArrayList<String> ConvertStringToArrayList(String data) {
				ArrayList<String> cardList = new ArrayList<String>();
				
				char[] tmpChar = data.toCharArray();
				byte[] tmpByte = new byte[tmpChar.length];
				
				for(int i = 0; i < tmpChar.length; i++) {
					tmpByte[i] = (byte)tmpChar[i];
				}
				
				int totalLen = tmpChar.length;
				int processedLen = 0;
				int currentCardLen = 0;
				byte[] currentCard;
				
				while(totalLen > processedLen) {
					currentCardLen = tmpByte[processedLen++];
					currentCard = new byte[currentCardLen];
					
					for(int i = 0; i < currentCardLen; i++) {
						currentCard[i] = tmpByte[processedLen++];
					}
					cardList.add(DatatypeConverter.printHexBinary(currentCard));
				}
				
				return cardList;
			}
			
		};
	}
	
	private Runnable getTimerRunn() {
		return new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(120000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized(returnList) {
						returnList.clear();
						returnList = new HashSet<>();
					}
				}
			}
			
		};
	}
	
	

	
	public String getStudentID() {
		String tmp = null;
		while(true) {
			synchronized(returnList) {
				synchronized(studentQueue) {
					tmp = studentQueue.poll();
					if(tmp == null) {
						break;
					} else if(!returnList.contains(tmp)) {
						returnList.add(tmp);
						break;
					}
				}
			}
		}
		return tmp;
	}
}
