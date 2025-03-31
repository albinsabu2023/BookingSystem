package com.booking.model;

public class User {
	private int empId;
	private String name;
	private String password;
	
	
	
	@Override
	public String toString() {
		return "User [empId=" + empId + ", name=" + name + ", password=" + password + "]";
	}
	public User(int empId, String name, String password) {
		this.empId = empId;
		this.name = name;
		this.password = password;
	}
	public User(int id, String psd) {
		// TODO Auto-generated constructor stub
		this.empId=id;
		this.password=psd;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
