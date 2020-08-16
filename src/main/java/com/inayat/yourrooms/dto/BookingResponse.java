package com.inayat.yourrooms.dto;

import java.util.Date;

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
	 private Date checkinDate;
	 private Date checkout_date;
	 private String checkout_status;
	 private String checkin_status;
	 private String bookingId;
	 private Long userId;
	 private Double merchantDiscount;
	 
	
	public Double getMerchantDiscount() {
		return merchantDiscount;
	}
	public void setMerchantDiscount(Double merchantDiscount) {
		this.merchantDiscount = merchantDiscount;
	}
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
	public Date getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}
	public Date getCheckout_date() {
		return checkout_date;
	}
	public void setCheckout_date(Date checkout_date) {
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCheckout_status() {
		return checkout_status;
	}
	public void setCheckout_status(String checkout_status) {
		this.checkout_status = checkout_status;
	}
	public String getCheckin_status() {
		return checkin_status;
	}
	public void setCheckin_status(String checkin_status) {
		this.checkin_status = checkin_status;
	}
	
	 

}
