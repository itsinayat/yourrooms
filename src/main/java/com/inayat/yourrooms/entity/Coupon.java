package com.inayat.yourrooms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "t_coupons")
public class Coupon implements Serializable{
	
	
	public Coupon() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code", unique = true)
	private String code;
	
	@Column(name = "value")
	private Double value;
	
	@Column(name = "enabled")
	private Boolean enabled;
	

	@Column(name ="expiry")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date expiry;

	public Coupon(String code, Double value,Boolean enabled, Date expiry) {
		this.code=code;
		this.value =value;
		this.enabled = enabled;
		this.expiry = expiry;
	}
	

	public Coupon(long id, String code, Double value, boolean enabled, Date expiry) {
		this.code=code;
		this.value =value;
		this.enabled = enabled;
		this.expiry = expiry;
		this.id =id;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public Date getExpiry() {
		return expiry;
	}


	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	

}
