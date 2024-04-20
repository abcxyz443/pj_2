package com.javaweb.springbootnonjwt.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
   private static  String url= "jdbc:mysql://localhost:3307/estatebasic";
   private static String user = "root";
   private static String pass = "root";

   public static final Connection getConnection(){
       try{
           Connection conn = DriverManager.getConnection(url, user, pass);
           return conn;
       }catch (SQLException err){
           err.printStackTrace();
       }
       return null;
   }
}
