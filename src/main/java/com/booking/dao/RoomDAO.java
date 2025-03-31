package com.booking.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.booking.model.Room;

public class RoomDAO {
	private final static String  GET_ALL_ROOMS="select * from rooms";
	static List<Room> rooms=new ArrayList<>();
	public static List<Room> getRooms(Connection con) throws SQLException{
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs=st.executeQuery(GET_ALL_ROOMS);
			while(rs.next()) {
				
				int id=rs.getInt("roomId");
				 String name=rs.getString("name");
				 String location=rs.getString("location");
				 int capacity=rs.getInt("capacity");
				 String status=rs.getString("status");
				 String specifications=rs.getString("specifications");
				 Room r=new Room(id,name,location,capacity,status,specifications);
				 rooms.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error while executing query");
			e.printStackTrace();
		}	
		
		return rooms;
		
	}
}
