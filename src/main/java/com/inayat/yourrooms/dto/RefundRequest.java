package com.inayat.yourrooms.dto;

public class RefundRequest {
	Long bookingId;
	Double refundAmount;
	
	
	public RefundRequest(Long bookingId, Double refundAmount) {
		super();
		this.bookingId = bookingId;
		this.refundAmount = refundAmount;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

}
