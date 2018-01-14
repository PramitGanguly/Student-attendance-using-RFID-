package com.SohojWeb.DSI.UHF_Reader.JNI;

public class UHF_Reader_JNI {
	static {
		System.loadLibrary("UHF_JNI");
		System.loadLibrary("UHFReader18");
	}
	public native String Inventory_G2_TCPIP(String ip, int port);
	public native String Inventory_G2_EPC_TCPIP(String ip, int port);
	public native byte WriteEPC_G2_EPC_TCPIP(String ip, int port, byte[] epcdata, byte epcdatalen);

}
