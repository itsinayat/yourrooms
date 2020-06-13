package com.inayat.yourrooms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_refund")
public class Refunds {
public Refunds() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "refund_id")
	private String refundId;

	@Column(name = "payment_id")
	private String paymentId;

	@Column(name = "status")
	private String status;

	
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "refund_amount")
	private Double refundAmount;

	@Column(name = "created_at")
	private String createdAt;
	
	@Column(name = "booking_id")
	private Long bookingId;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public Refunds(String refundId, String paymentId, String status, Double totalAmount, Double refundAmount,
			String createdAt, Long bookingId) {
		super();
		this.refundId = refundId;
		this.paymentId = paymentId;
		this.status = status;
		this.totalAmount = totalAmount;
		this.refundAmount = refundAmount;
		this.createdAt = createdAt;
		this.bookingId = bookingId;
	}
	

}
