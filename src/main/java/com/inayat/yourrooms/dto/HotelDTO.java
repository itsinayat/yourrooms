package com.inayat.yourrooms.dto;

import java.util.Date;

public class HotelDTO {
	
	private Long id;
	
	private String hotelName;

	private String address;

	private String lattitude;

	private String longitude;

	private Boolean payAtHotel;

	private Boolean freeBreakFast;

	private Boolean coupleFriendly;

	private String images;

	private Boolean freeWifi;

	private Long initialPrice;

	private Long discountPrice;

	private Long rating;

	private Boolean del_ind;

	private Date create_dt;

	private Date update_dt;

	private Long create_user_id;

	private Long update_user_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Boolean getPayAtHotel() {
		return payAtHotel;
	}

	public void setPayAtHotel(Boolean payAtHotel) {
		this.payAtHotel = payAtHotel;
	}

	

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Boolean getFreeWifi() {
		return freeWifi;
	}

	public void setFreeWifi(Boolean freeWifi) {
		this.freeWifi = freeWifi;
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

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public Boolean getDel_ind() {
		return del_ind;
	}

	public void setDel_ind(Boolean del_ind) {
		this.del_ind = del_ind;
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

	public Boolean getFreeBreakFast() {
		return freeBreakFast;
	}

	public void setFreeBreakFast(Boolean freeBreakFast) {
		this.freeBreakFast = freeBreakFast;
	}

	public Boolean getCoupleFriendly() {
		return coupleFriendly;
	}

	public void setCoupleFriendly(Boolean coupleFriendly) {
		this.coupleFriendly = coupleFriendly;
	}

	

}
