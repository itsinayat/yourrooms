package com.inayat.yourrooms.model;

public class OTPResponse {
	private String otp;
	private Boolean regInd;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Boolean getRegInd() {
		return regInd;
	}

	public OTPResponse(String otp, Boolean regInd) {
		super();
		this.otp = otp;
		this.regInd = regInd;
	}

	public void setRegInd(Boolean regInd) {
		this.regInd = regInd;
	}

}
