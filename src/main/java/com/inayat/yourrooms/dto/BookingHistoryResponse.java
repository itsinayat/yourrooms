package com.inayat.yourrooms.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.inayat.yourrooms.entity.Hotel;

public class BookingHistoryResponse implements Serializable {
	Long id;
	Double price;
	String discount_coupon;
	Double discount_price;
	Double coupon_discount;
	Double gst;
	String rooms;
	Hotel hotel;
	int noOfGuests;
	BookingTransactionDTO transaction;
	String create_dt;
	Long create_user_id;
	String bookingStatus;
	String checkinDate;
	String checkoutDate;
	String bookingId;
	private String checkin_status;
	
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	private String checkout_status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDiscount_coupon() {
		return discount_coupon;
	}
	public void setDiscount_coupon(String discount_coupon) {
		this.discount_coupon = discount_coupon;
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
	
	
	
	public String getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(String create_dt) {
		this.create_dt = create_dt;
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
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public Long getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(Long create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getRooms() {
		return rooms;
	}
	public void setRooms(String rooms) {
		this.rooms = rooms;
	}
	public BookingTransactionDTO getTransaction() {
		return transaction;
	}
	public void setTransaction(BookingTransactionDTO transaction) {
		this.transaction = transaction;
	}
	public String getCheckin_status() {
		return checkin_status;
	}
	public void setCheckin_status(String checkin_status) {
		this.checkin_status = checkin_status;
	}
	public String getCheckout_status() {
		return checkout_status;
	}
	public void setCheckout_status(String checkout_status) {
		this.checkout_status = checkout_status;
	}
	

}
