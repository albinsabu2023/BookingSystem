package com.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.booking.model.User;

public class LoginDAO {
	private static final String INSERT_USER="insert into users values (?,?,?)";
	private static final String SELECT_USER_BY_ID_AND_PSD="select * from users where employee_id=? and password=?";
	public static String register(Connection con,User u) {
		PreparedStatement st;
		try {
			st = con.prepareStatement(INSERT_USER);
			st.setInt(1,u.getEmpId());
			st.setString(2,u.getName());
			st.setString(3,u.getPassword());
			st.execute();
			return "user registerd succesfully";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("some error occured while registering");
			e.printStackTrace();
		}
		return "user registration faild";
		
	}
	public static boolean isAdmin(Connection con,User u) {
		int id=u.getEmpId();
		String password=u.getPassword();
		return (id==1234 && password.equals("admin123"));
	}
//	public static boolean authenticate(Connection con,User u) {
//		PreparedStatement st;
//		try {
//			System.out.println("auth dao called");
//			st=con.prepareStatement(SELECT_USER_BY_ID_AND_PSD);
//			st.setInt(1, u.getEmpId());
//			st.setString(2, u.getPassword());
//			ResultSet rs=st.executeQuery();
//			
//			System.out.println( rs.next()?"user exists":"");
//			return rs.next();
//		} catch (SQLException e) {
//			System.out.println("error while authenticating");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
	public static boolean authenticate(Connection con, User u) {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        System.out.println("auth dao called");
	        st = con.prepareStatement(SELECT_USER_BY_ID_AND_PSD);
	        st.setInt(1, u.getEmpId());
	        st.setString(2, u.getPassword());
	        rs = st.executeQuery();
	        
	        // Move cursor to the first row and check if it exists
	        boolean userExists = rs.next();
	        
	        System.out.println(userExists ? "User exists" : "User does not exist");
	        
	        return userExists;  // Return true if user exists, false otherwise
	    } catch (SQLException e) {
	        System.out.println("Error while authenticating");
	        e.printStackTrace();
	    } 
	    
	    return false;  // Return false if an error occurred or no user found
	}


}
