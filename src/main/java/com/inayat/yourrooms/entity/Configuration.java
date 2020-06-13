package com.inayat.yourrooms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_configuration")
public class Configuration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Configuration() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@Column(name = "key")
	private String key;
	
	@Column(name = "value")
	private String value;
	

	public Configuration(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
}
