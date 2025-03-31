package com.booking.model;

public class Room {
	private int id;
	private String name;
	private String location;
	private int capacity;
	private String status;
	private String specifications;
	
	
	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", location=" + location + ", capacity=" + capacity + ", status="
				+ status + ", specifications=" + specifications + "]";
	}
	
	public Room(int id, String name, String location, int capacity, String status, String specifications) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.capacity = capacity;
		this.status = status;
		this.specifications = specifications;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	public boolean isActive() {
		return this.status.equalsIgnoreCase("active");
	}
	
	
}	
