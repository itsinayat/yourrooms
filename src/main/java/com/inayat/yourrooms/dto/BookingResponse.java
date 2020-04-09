package com.inayat.yourrooms.dto;

import com.inayat.yourrooms.entity.BookingTransaction;

public class BookingResponse {
	 private Long id;
	 private Double booking_price;
	 private Double discount_price;
	 private Double coupon_discount;
	 private String discount_coupon;
	 private Double gst;
	 private Long[] rooms;
	 private int  noOfGuests;
	 private BookingTransaction transaction;
	 private String bookingStatus;
	 private String paymentStatus;
	 private String checkinDate;
	 private String checkout_date;
	 private String bookingId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getBooking_price() {
		return booking_price;
	}
	public void setBooking_price(Double booking_price) {
		this.booking_price = booking_price;
	}
	
	public Double getGst() {
		return gst;
	}
	public void setGst(Double gst) {
		this.gst = gst;
	}
	
	public int getNoOfGuests() {
		return noOfGuests;
	}
	public void setNoOfGuests(int noOfGuests) {
		this.noOfGuests = noOfGuests;
	}
	public BookingTransaction getTransaction() {
		return transaction;
	}
	public void setTransaction(BookingTransaction transaction) {
		this.transaction = transaction;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
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
	public String getCheckout_date() {
		return checkout_date;
	}
	public void setCheckout_date(String checkout_date) {
		this.checkout_date = checkout_date;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getDiscount_coupon() {
		return discount_coupon;
	}
	public void setDiscount_coupon(String discount_coupon) {
		this.discount_coupon = discount_coupon;
	}
	public Long[] getRooms() {
		return rooms;
	}
	public void setRooms(Long[] rooms) {
		this.rooms = rooms;
	}
	public Double getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(Double discount_price) {
		this.discount_price = discount_price;
	}
	public Double getCoupon_discount() {
		return coupon_discount;
	}
	public void setCoupon_discount(Double coupon_discount) {
		this.coupon_discount = coupon_discount;
	}
	 

}
