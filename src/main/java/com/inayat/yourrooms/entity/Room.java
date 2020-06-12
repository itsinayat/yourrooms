package com.inayat.yourrooms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "t_rooms")
public class Room implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "initial_price")
	private Long initialPrice;

	@Column(name = "discount_price")
	private Long discountPrice;

	@Column(name = "free_cancellation")
	private Boolean freeCancellation;

	@Column(name = "balcony_avl")
	private Boolean balconyAvl;

	@Column(name = "double_bed")
	private Boolean doubleBed;

	@Column(name = "occupacy")
	private Long occupacy;
	
	@Column(name = "avialable")
	private Long avialable;

	@Column(name = "room_size")
	private String roomSize;

	// premium/exedc/del
	@Column(name = "room_type")
	private String roomType;

	@Column(name = "reserved")
	private Boolean reserved=false;
	
	@Column(name = "disabled")
	private Boolean disabled=false;

	@CreationTimestamp
	@Column(name = "create_dt")
	private Date create_dt;

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	@UpdateTimestamp
	@Column(name = "update_dt")
	private Date update_dt;

	@Column(name = "create_user_id")
	private Long create_user_id;

	@Column(name = "update_user_id")
	private Long update_user_id;

	@ManyToOne
	private Hotel hotel;

	@OneToMany(mappedBy = "rms", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<RoomImage> roomImages;

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

	@JsonIgnore
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Long getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Long initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Long getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Long discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Date getCreate_dt() {
		return create_dt;
	}

	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}

	public Date getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(Date update_dt) {
		this.update_dt = update_dt;
	}

	public Long getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(Long create_user_id) {
		this.create_user_id = create_user_id;
	}

	public Long getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}

	public Set<RoomImage> getRoomImages() {
		return roomImages;
	}

	public void setRoomImages(Set<RoomImage> roomImages) {
		this.roomImages = roomImages;
	}

	public Long getAvialable() {
		return avialable;
	}

	public void setAvialable(Long avialable) {
		this.avialable = avialable;
	}

}
