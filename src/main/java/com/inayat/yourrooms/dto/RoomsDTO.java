package com.inayat.yourrooms.dto;

import java.util.Date;

import com.inayat.yourrooms.entity.Hotel;

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
	
	private Hotel hotel;

	
	private Date create_dt;

	private Date update_dt;

	private Long create_user_id;

	private Long update_user_id;
	
	private String images;
	

	
	
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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
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

	public Date getCreate_dt() {
		return create_dt;
	}

	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}

	public Date getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(Date update_dt) {
		this.update_dt = update_dt;
	}

	public Long getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(Long create_user_id) {
		this.create_user_id = create_user_id;
	}

	public Long getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	

}
