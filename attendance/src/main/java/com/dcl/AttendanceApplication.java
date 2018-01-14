package com.dcl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dcl.controller.DataBaseController;

@SpringBootApplication
public class AttendanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
		DataBaseController.connect();
	DataBaseController.Update_cont("123", 4277);
		
	
	
		
	
	}
}
