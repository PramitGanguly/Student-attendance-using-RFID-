package com.dcl.controller;

import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONConvertor {
	
	public static JSONArray convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
        	 JSONObject obj = new JSONObject();
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
               
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
              
            }
            jsonArray.put(obj);
        }
        return jsonArray;
    }
}
