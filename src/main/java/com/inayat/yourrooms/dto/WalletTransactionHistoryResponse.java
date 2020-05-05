package com.inayat.yourrooms.dto;

import java.util.Date;

public class WalletTransactionHistoryResponse {
	  Long id;
      Long amount;
      String tr_type;
      String comment;
      String referenceID;
      Date create_dt;
      
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getTr_type() {
		return tr_type;
	}
	public void setTr_type(String tr_type) {
		this.tr_type = tr_type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getReferenceID() {
		return referenceID;
	}
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
}
