package com.booking.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.booking.model.Booking;

public class BookingDAO {
	private final static String  GET_ALL_BOOKINGS="select * from bookings";
	
	public static List<Booking> getBookings(Connection con) throws SQLException{
		List<Booking> bookings =new ArrayList<>();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(GET_ALL_BOOKINGS);
//		this.bookingId = bookingId;
//		this.empId = empId;
//		this.roomId = roomId;
//		this.bookDate = bookDate;
//		this.fromTime = fromTime;
//		this.toTime = toTime;
//		this.status = status;
//		this.purpose = purpose;
//		this.createdAt = createdAt;
		while(rs.next()) {
			 int bId = rs.getInt("booking_id");
			    int eId = rs.getInt("employee_id");
			    int rId = rs.getInt("roomId");
			    Date bDate = rs.getDate("date");
			    Time fTime = rs.getTime("fromtime");
			    Time tTime = rs.getTime("totime");
			    String status = rs.getString("status");
			    
			Booking b=new Booking(bId,eId,rId,bDate,tTime,fTime,status);
			bookings.add(b);
		}
		
		return bookings;
	}
}
