package com.inayat.yourrooms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_hotels")
public class Hotels implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "hotel_name")
	private String hotelName;

	@Column(name = "address")
	private String address;

	@Column(name = "lattitude")
	private String lattitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "pay_at_hotel")
	private Boolean payAtHotel;

	@Column(name = "free_breakfast")
	private Boolean FreeBreakFast;

	@Column(name = "couple_friendly")
	private Boolean CoupleFriendly;

	@Column(name = "images")
	private String images;

	@Column(name = "free_wifi")
	private Boolean freeWifi;

	@Column(name = "initial_price")
	private Long initialPrice;

	@Column(name = "discount_price")
	private Long discountPrice;

	@Column(name = "rating")
	private Long rating;

	@Column(name = "del_ind")
	private Boolean del_ind;

	@Column(name = "create_dt")
	private Date create_dt;

	@Column(name = "update_dt")
	private Date update_dt;

	@Column(name = "create_user_id")
	private Long create_user_id;

	@Column(name = "update_user_id")
	private Long update_user_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Boolean getPayAtHotel() {
		return payAtHotel;
	}

	public void setPayAtHotel(Boolean payAtHotel) {
		this.payAtHotel = payAtHotel;
	}

	public Boolean getFreeBreakFast() {
		return FreeBreakFast;
	}

	public void setFreeBreakFast(Boolean freeBreakFast) {
		FreeBreakFast = freeBreakFast;
	}

	public Boolean getCoupleFriendly() {
		return CoupleFriendly;
	}

	public void setCoupleFriendly(Boolean coupleFriendly) {
		CoupleFriendly = coupleFriendly;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Boolean getFreeWifi() {
		return freeWifi;
	}

	public void setFreeWifi(Boolean freeWifi) {
		this.freeWifi = freeWifi;
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

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public Boolean getDel_ind() {
		return del_ind;
	}

	public void setDel_ind(Boolean del_ind) {
		this.del_ind = del_ind;
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
	

}
