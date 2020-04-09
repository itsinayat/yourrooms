package com.inayat.yourrooms.dto;

import javax.persistence.Column;

import com.inayat.yourrooms.entity.Hotels;

public class RoomsDTO {

	private Long id;

	private Long initialPrice;
	
	private Long discountPrice;

	private String name;

	private Boolean freeCancellation;

	private Boolean balconyAvl;

	private Boolean doubleBed;

	private Long occupacy;

	private String roomSize;

	private String roomType;

	private Boolean reserved;

	private Long hotelId;
	
	private Hotels hotel;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getFreeCancellation() {
		return freeCancellation;
	}

	public void setFreeCancellation(Boolean freeCancellation) {
		this.freeCancellation = freeCancellation;
	}

	public Boolean getBalconyAvl() {
		return balconyAvl;
	}

	public void setBalconyAvl(Boolean balconyAvl) {
		this.balconyAvl = balconyAvl;
	}

	public Boolean getDoubleBed() {
		return doubleBed;
	}

	public void setDoubleBed(Boolean doubleBed) {
		this.doubleBed = doubleBed;
	}

	public Long getOccupacy() {
		return occupacy;
	}

	public void setOccupacy(Long occupacy) {
		this.occupacy = occupacy;
	}

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Hotels getHotel() {
		return hotel;
	}

	public void setHotel(Hotels hotel) {
		this.hotel = hotel;
	}

	public Long getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Long initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Long getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Long discountPrice) {
		this.discountPrice = discountPrice;
	}

	

}
