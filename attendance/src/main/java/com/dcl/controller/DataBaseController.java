package com.dcl.controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DataBaseController {
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet res = null; 
	private static String dates = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	private static String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
	
	static {
		try{
		 	Class.forName("com.mysql.jdbc.Driver");
	 	}
	 catch(ClassNotFoundException e){
		 
		 System.out.println("driver not found");
	 }
	}
	
	static {
		try{
			 
			 conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/attendance?autoReconnect=true","root","");
			 System.out.println("connected");
			 
		 }
		 
		 catch(SQLException e){
			
			 System.out.println("Connection Fail");
			 
		 }
	}
	/*
	  * this connect for connect 
	  * <................................start...................................>
	  */
	 public static void connect(){
		 
		 
		 try{
			 
			 conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/attendance?autoReconnect=true","root","");
			 System.out.println("connected");
			 
		 }
		 
		 catch(SQLException e){
			
			 System.out.println("Connection Fail");
			 
		 }
		 
	  }
	 //<.................................end..................................>
	 
	 
	 
	 //get visitor count function 
		public static Map<String, Object> getVisitorCount(){
			connect();
			Map<String, Object> model = new HashMap<String, Object>();
		    String visitor;
			
			 String sql = "SELECT max(date) AS `day`, COUNT(attendance_count) AS `visitor` FROM `attendance_user` WHERE attendance_count%2 = 1 and date >= '"+dates+" 00:00:00' and date <= '"+dates+" 23:59:59'";
			 try {	
					   //PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
					stmt= (Statement) conn.createStatement();
					res = stmt.executeQuery(sql);
				       //res = pst.executeQuery();
				       
				         while  (res.next())
				      {
				        	visitor = res.getString("visitor");
						    model.put("visitor",visitor);
		
				       }
		  }
			catch(Exception e){
				//e.printStackTrace();
	        	
				 model.put("visitor",0);
				 //System.out.println("Fail to get visitor cout");
			}
			 
		  return model;
		}
		
		 //<................................. get visitor count function  end ..................................>
		
		
		
		
		 //get getLastFiveData count function 
		public static String getLastFiveData(){
			
			connect();
		    String JsonAry = null;
			String dates = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			 String sql = "SELECT * from card_holders c left join attendance_user a on a.uhf_tag_id=c.uhf_tag_id where a.date >= '"+dates+" 00:00:00' and a.date <= '"+dates+" 23:59:59' ORDER by c.id DESC LIMIT 0, 6";
			 try {
						PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
						res = pst.executeQuery();
						JsonAry =  JSONConvertor.convertToJSON(res).toString();
				   
		      }
			 
			catch(Exception e){
				 System.out.println("Fail to get getLastFiveData ");
			}
			 	System.out.println(JsonAry);
		  return JsonAry;
		}
		
		 //<.................................getLastFiveData function  end ..................................>
		
		
		
		//insert attendance info function 
		
		 public static void Insert_attendance_info(String uhf_tag_id,int attendance_count,String ip_address){
				
			 Statement stm = null;
				String sql = "INSERT INTO `attendance_user` (`uhf_tag_id`, `modify_date`, `attendance_count`, `ip_address`) VALUES ('"+uhf_tag_id+"','"+timeStamp+"','"+attendance_count+"' ,'"+ip_address+"')";
				try {
					stm= (Statement) conn.createStatement();
					stm.executeUpdate(sql);
			       System.out.println( "user Input Successfull");
				}
				catch (SQLException e1) {
				
				System.out.println("cant insert");
				}
				
			 }
		 
		//insert attendance info function 
/*
 * it return the id if no id ,it will return null
 */
		 public static String get_attendance_id(String uhf_tag_id){
			 String id = null;
			
				String sql = "SELECT * FROM `attendance_user`WHERE uhf_tag_id = "+uhf_tag_id+" AND  date >= '"+dates+" 00:00:00' and date <= '"+dates+" 23:59:59' ";
				try {
					stmt= (Statement) conn.createStatement();
					res = stmt.executeQuery(sql);
					
					 while(res.next()){
					      
						id =  res.getString("attendance_id");
				      }
			     
				}
				catch (SQLException e1) {
				
				System.out.println("cant get id");
				}
				
				return id;
			 }
		 
		 
		 public static int get_current_count(String id){
			 int count = 0;
			
				String sql = "SELECT * FROM `attendance_user`WHERE attendance_id = '"+id+"' AND  date >= '"+dates+" 00:00:00' and date <= '"+dates+" 23:59:59' ";
				try {
					stmt= (Statement) conn.createStatement();
					res = stmt.executeQuery(sql);
					
					 while(res.next()){
					      
						 count =  res.getInt("attendance_count");
				      }
			     
				}
				catch (SQLException e1) {
				
				System.out.println("cant get count");
			
				}
				
				return count;
			 }
		
		 
		 
		 
		 /*
		  * it return the id if no id ,it will return null
		  */

		 		
		 		 
		 		public static void Update_cont(String id, int newCount){
		 			 
		 			
		 			 
		 			 PreparedStatement pst = null;
		 				String sql = "UPDATE attendance_user SET `modify_date` = ?,`attendance_count` = ? WHERE attendance_id = ? ";
		 				
		 				
		 				try {
		 					
		 				
		 					
		 				
		 				   pst =(PreparedStatement)conn.prepareStatement(sql);
		 				 
		 			       pst.setString(1, timeStamp); 
		 			       pst.setInt(2, newCount); 
		 			       pst.setString(3, id);
		 			  
		 			       pst.executeUpdate();
		 			       
		 			       System.out.println("update done");
		 					
		 				}
		 				catch (SQLException e1) {
		 					 System.out.println("error update");
		 				
		 				}
		 		 }
		 
		
		
		 		public static java.sql.Date getCurrentDatetime() {
		 		    java.util.Date today = new java.util.Date();
		 		    return new java.sql.Date(today.getTime());
		 		}

}
