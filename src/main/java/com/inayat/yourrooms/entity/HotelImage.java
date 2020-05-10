package com.inayat.yourrooms.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "t_images_hotel")
public class HotelImage {
	
	public HotelImage() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "url")
	private String url;
	
	
	@ManyToOne
	@JsonBackReference
    private Hotel htl;

	

	public HotelImage(String url, Hotel htl) {
		super();
		this.url = url;
		this.htl = htl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	


	public Hotel getHtl() {
		return htl;
	}

	public void setHtl(Hotel htl) {
		this.htl = htl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	

}
