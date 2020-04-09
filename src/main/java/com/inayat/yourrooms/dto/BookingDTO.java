package com.inayat.yourrooms.dto;

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
	
	private Long booking_price;
	
	private Long coupon_discount;
	
	private Long discount_price;
	
	private Long gst;
	
	
	
	

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

	public Long getBooking_price() {
		return booking_price;
	}

	public void setBooking_price(Long booking_price) {
		this.booking_price = booking_price;
	}

	public Long getCoupon_discount() {
		return coupon_discount;
	}

	public void setCoupon_discount(Long coupon_discount) {
		this.coupon_discount = coupon_discount;
	}

	public Long getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(Long discount_price) {
		this.discount_price = discount_price;
	}

	public Long getGst() {
		return gst;
	}

	public void setGst(Long gst) {
		this.gst = gst;
	}

	
	

}
