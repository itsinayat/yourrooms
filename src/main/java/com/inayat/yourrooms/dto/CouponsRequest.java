package com.inayat.yourrooms.dto;

import java.util.Date;

public class CouponsRequest {
	
	private Long id;

	private String code;
	
	private Double value;
	
	private Date expiry;
	
	private Boolean enabled;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public CouponsRequest(Long id, String code, Double value, Date expiry,Boolean enabled) {
		super();
		this.code = code;
		this.value = value;
		this.expiry = expiry;
		this.id = id;
		this.enabled = enabled;
		
	}
	
	

}
