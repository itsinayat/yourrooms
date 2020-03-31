package com.inayat.yourrooms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_rooms")
public class Rooms implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="free_cancellation")
	private Boolean freeCancellation;
	
	@Column(name="balcony_avl")
	private Boolean balconyAvl;
	
	@Column(name="double_bed")
	private Boolean doubleBed;
	
	@Column(name="occupacy")
	private Long occupacy;
	
	@Column(name="room_size")
	private String roomSize;
	
	//premium/exedc/del
	@Column(name="room_type")
	private String roomType;
	
	@Column(name="reserved")
	private Boolean reserved;
	
	@ManyToOne
	private Hotels hotel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getFreeCancellation() {
		return freeCancellation;
	}

	public void setFreeCancellation(Boolean freeCancellation) {
		this.freeCancellation = freeCancellation;
	}

	public Boolean getBalconyAvl() {
		return balconyAvl;
	}

	public void setBalconyAvl(Boolean balconyAvl) {
		this.balconyAvl = balconyAvl;
	}

	public Boolean getDoubleBed() {
		return doubleBed;
	}

	public void setDoubleBed(Boolean doubleBed) {
		this.doubleBed = doubleBed;
	}

	public Long getOccupacy() {
		return occupacy;
	}

	public void setOccupacy(Long occupacy) {
		this.occupacy = occupacy;
	}

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	public Hotels getHotel() {
		return hotel;
	}

	public void setHotel(Hotels hotel) {
		this.hotel = hotel;
	}
	
}
