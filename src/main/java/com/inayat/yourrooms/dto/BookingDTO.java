package com.inayat.yourrooms.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class BookingDTO {
	
	
	
	private Long id;

	private Long userId;

	private Long[] rooms;

	private int noOfGuests;

	private String BookingId;

	private Boolean del_ind;

	private Long create_user_id;

	private Long update_user_id;

	private String bookingStatus;
	
	private String paymentStatus;
	
	private String checkinDate;
	
	private String checkoutDate;
	
	private Double booking_price;
	
	private Double coupon_discount;
	
	private Double discount_price;
	
	private Double gst;
	
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public int getNoOfGuests() {
		return noOfGuests;
	}

	public void setNoOfGuests(int noOfGuests) {
		this.noOfGuests = noOfGuests;
	}

	public String getBookingId() {
		return BookingId;
	}

	public void setBookingId(String bookingId) {
		BookingId = bookingId;
	}

	public Boolean getDel_ind() {
		return del_ind;
	}

	public void setDel_ind(Boolean del_ind) {
		this.del_ind = del_ind;
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

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Long[] getRooms() {
		return rooms;
	}

	public void setRooms(Long[] rooms) {
		this.rooms = rooms;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	

	

	

	public Double getBooking_price() {
		return booking_price;
	}

	public void setBooking_price(Double booking_price) {
		this.booking_price = booking_price;
	}

	public Double getCoupon_discount() {
		return coupon_discount;
	}

	public void setCoupon_discount(Double coupon_discount) {
		this.coupon_discount = coupon_discount;
	}

	public Double getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(Double discount_price) {
		this.discount_price = discount_price;
	}

	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BookingId == null) ? 0 : BookingId.hashCode());
		result = prime * result + ((bookingStatus == null) ? 0 : bookingStatus.hashCode());
		result = prime * result + ((booking_price == null) ? 0 : booking_price.hashCode());
		result = prime * result + ((checkinDate == null) ? 0 : checkinDate.hashCode());
		result = prime * result + ((checkoutDate == null) ? 0 : checkoutDate.hashCode());
		result = prime * result + ((coupon_discount == null) ? 0 : coupon_discount.hashCode());
		result = prime * result + ((create_user_id == null) ? 0 : create_user_id.hashCode());
		result = prime * result + ((del_ind == null) ? 0 : del_ind.hashCode());
		result = prime * result + ((discount_price == null) ? 0 : discount_price.hashCode());
		result = prime * result + ((gst == null) ? 0 : gst.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + noOfGuests;
		result = prime * result + ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
		result = prime * result + Arrays.hashCode(rooms);
		result = prime * result + ((update_user_id == null) ? 0 : update_user_id.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	

}
