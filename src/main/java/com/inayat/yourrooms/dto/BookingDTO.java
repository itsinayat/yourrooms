package com.inayat.yourrooms.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class BookingDTO {

	private Long id;
	
	private Long hotelId;

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	private Long userId;

	private Long[] rooms;

	private int noOfGuests;

	private String BookingId;

	private Boolean del_ind;

	private Long create_user_id;

	private Long update_user_id;

	private String bookingStatus;

	private String paymentStatus;

	private String checkinDate;

	private String checkoutDate;
	private String checkoutStatus;
	private String checkinStatus;

	private Double booking_price;

	private Double coupon_discount;

	private Double discount_price;

	private Double gst;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getNoOfGuests() {
		return noOfGuests;
	}

	public void setNoOfGuests(int noOfGuests) {
		this.noOfGuests = noOfGuests;
	}

	public String getBookingId() {
		return BookingId;
	}

	public void setBookingId(String bookingId) {
		BookingId = bookingId;
	}

	public Boolean getDel_ind() {
		return del_ind;
	}

	public void setDel_ind(Boolean del_ind) {
		this.del_ind = del_ind;
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

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Long[] getRooms() {
		return rooms;
	}

	public void setRooms(Long[] rooms) {
		this.rooms = rooms;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public Double getBooking_price() {
		return booking_price;
	}

	public void setBooking_price(Double booking_price) {
		this.booking_price = booking_price;
	}

	public Double getCoupon_discount() {
		return coupon_discount;
	}

	public void setCoupon_discount(Double coupon_discount) {
		this.coupon_discount = coupon_discount;
	}

	public Double getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(Double discount_price) {
		this.discount_price = discount_price;
	}

	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	public String getCheckoutStatus() {
		return checkoutStatus;
	}

	public void setCheckoutStatus(String checkoutStatus) {
		this.checkoutStatus = checkoutStatus;
	}

	public String getCheckinStatus() {
		return checkinStatus;
	}

	public void setCheckinStatus(String checkinStatus) {
		this.checkinStatus = checkinStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BookingId == null) ? 0 : BookingId.hashCode());
		result = prime * result + ((bookingStatus == null) ? 0 : bookingStatus.hashCode());
		result = prime * result + ((booking_price == null) ? 0 : booking_price.hashCode());
		result = prime * result + ((checkinDate == null) ? 0 : checkinDate.hashCode());
		result = prime * result + ((checkinStatus == null) ? 0 : checkinStatus.hashCode());
		result = prime * result + ((checkoutDate == null) ? 0 : checkoutDate.hashCode());
		result = prime * result + ((checkoutStatus == null) ? 0 : checkoutStatus.hashCode());
		result = prime * result + ((coupon_discount == null) ? 0 : coupon_discount.hashCode());
		result = prime * result + ((create_user_id == null) ? 0 : create_user_id.hashCode());
		result = prime * result + ((del_ind == null) ? 0 : del_ind.hashCode());
		result = prime * result + ((discount_price == null) ? 0 : discount_price.hashCode());
		result = prime * result + ((gst == null) ? 0 : gst.hashCode());
		result = prime * result + ((hotelId == null) ? 0 : hotelId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + noOfGuests;
		result = prime * result + ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
		result = prime * result + Arrays.hashCode(rooms);
		result = prime * result + ((update_user_id == null) ? 0 : update_user_id.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingDTO other = (BookingDTO) obj;
		if (BookingId == null) {
			if (other.BookingId != null)
				return false;
		} else if (!BookingId.equals(other.BookingId))
			return false;
		if (bookingStatus == null) {
			if (other.bookingStatus != null)
				return false;
		} else if (!bookingStatus.equals(other.bookingStatus))
			return false;
		if (booking_price == null) {
			if (other.booking_price != null)
				return false;
		} else if (!booking_price.equals(other.booking_price))
			return false;
		if (checkinDate == null) {
			if (other.checkinDate != null)
				return false;
		} else if (!checkinDate.equals(other.checkinDate))
			return false;
		if (checkinStatus == null) {
			if (other.checkinStatus != null)
				return false;
		} else if (!checkinStatus.equals(other.checkinStatus))
			return false;
		if (checkoutDate == null) {
			if (other.checkoutDate != null)
				return false;
		} else if (!checkoutDate.equals(other.checkoutDate))
			return false;
		if (checkoutStatus == null) {
			if (other.checkoutStatus != null)
				return false;
		} else if (!checkoutStatus.equals(other.checkoutStatus))
			return false;
		if (coupon_discount == null) {
			if (other.coupon_discount != null)
				return false;
		} else if (!coupon_discount.equals(other.coupon_discount))
			return false;
		if (create_user_id == null) {
			if (other.create_user_id != null)
				return false;
		} else if (!create_user_id.equals(other.create_user_id))
			return false;
		if (del_ind == null) {
			if (other.del_ind != null)
				return false;
		} else if (!del_ind.equals(other.del_ind))
			return false;
		if (discount_price == null) {
			if (other.discount_price != null)
				return false;
		} else if (!discount_price.equals(other.discount_price))
			return false;
		if (gst == null) {
			if (other.gst != null)
				return false;
		} else if (!gst.equals(other.gst))
			return false;
		if (hotelId == null) {
			if (other.hotelId != null)
				return false;
		} else if (!hotelId.equals(other.hotelId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (noOfGuests != other.noOfGuests)
			return false;
		if (paymentStatus == null) {
			if (other.paymentStatus != null)
				return false;
		} else if (!paymentStatus.equals(other.paymentStatus))
			return false;
		if (!Arrays.equals(rooms, other.rooms))
			return false;
		if (update_user_id == null) {
			if (other.update_user_id != null)
				return false;
		} else if (!update_user_id.equals(other.update_user_id))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


	

}
