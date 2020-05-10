package com.inayat.yourrooms.dto;

public class MapStaffRequest {
	String hotelId;
	String[] staff;

	public MapStaffRequest() {  
		// TODO Auto-generated constructor stub
	}

	public MapStaffRequest(String hotelId, String[] staff) {
		super();
		this.hotelId = hotelId;
		this.staff = staff;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String[] getStaff() {
		return staff;
	}

	public void setStaff(String[] staff) {
		this.staff = staff;
	}

}
