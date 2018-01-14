package com.dcl.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SohojWeb.DSI.UHF_Reader.CardManager.CardManager;




@RestController
public class AppConteoller {
	
	
	private static String  ip_address = "192.168.10.146";
	private static String  uhf_id = null;
	private static String  attendance_id = null;
	
	CardManager card = new CardManager(ip_address, 6000);
	@RequestMapping("/vinfo")
	public Map<String, Object> visitorInfo() {
		
		Map<String, Object> model = DataBaseController.getVisitorCount();
		while(model.get("visitor").equals(0)) {
			model = DataBaseController.getVisitorCount();
		}
		return model;
	}
	
//last 5 student test code
@RequestMapping("/list")
public String LastFiveVisitor() {

		return DataBaseController.getLastFiveData();
	}


@RequestMapping("/checkid")
public Map<String, Object> Card() {
	
	Map<String, Object> model = DataBaseController.getVisitorCount();
	
		uhf_id = card.getStudentID();
		
		if(uhf_id.equals(null)){
			model.put("hasID",0);
		}
		else{
			attendance_id = DataBaseController.get_attendance_id(uhf_id);
				
			if(attendance_id.equals(null)){
				
				DataBaseController.Insert_attendance_info(uhf_id, 1, ip_address);
				model.put("hasID",1);
			}
			else{
				DataBaseController.Update_cont(attendance_id, DataBaseController.get_current_count(attendance_id));
				model.put("hasID",0);
			}
		}

		return model;
	}
	
	
	
}
