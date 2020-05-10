package com.inayat.yourrooms.translator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inayat.yourrooms.dto.BookingHistoryResponse;
import com.inayat.yourrooms.dto.BookingTransactionDTO;
import com.inayat.yourrooms.entity.BookingTransaction;
import com.inayat.yourrooms.entity.Booking;

public class BookingHistoryResponseTranslator implements Serializable {
	public static List<BookingHistoryResponse> translate(List<Booking> bookings) {
		List<BookingHistoryResponse> list =new ArrayList<>();
		for(Booking b:bookings) {
			BookingHistoryResponse h =new BookingHistoryResponse();
			h.setBookingId(b.getBookingId());
			h.setBookingStatus(b.getBookingStatus());
			h.setCheckinDate(b.getCheckinDate());
			h.setCheckoutDate(b.getCheckoutDate());
			h.setCoupon_discount(b.getCoupon_discount());
			h.setCreate_dt(b.getCreate_dt());
			h.setDiscount_coupon(b.getDiscount_coupon());
			h.setCreate_user_id(b.getCreate_user_id());
			h.setDiscount_price(b.getDiscount_price());
			h.setGst(b.getGst());
			h.setId(b.getId());
			h.setNoOfGuests(b.getNoOfGuests());
			h.setPrice(b.getBooking_price());
			h.setRooms(b.getRooms());
			h.setCheckin_status(b.getCheckin_status());
			h.setCheckout_status(b.getCheckout_status());
			
			
			BookingTransactionDTO dto =new BookingTransactionDTO();
			BookingTransaction tr= b.getTransaction();
			if(tr!=null) {
			dto.setCreate_dt(tr.getCreate_dt());
			dto.setCreate_user_id(tr.getCreate_user_id());
			dto.setDiscountType(tr.getDiscountType());
			dto.setId(tr.getId());
			dto.setPaidAmount(tr.getPaidAmount());
			dto.setPayment_mode(tr.getPayment_mode());
			dto.setPaymentId(tr.getPaymentId());
			dto.setPaymentStatus(tr.getPaymentStatus());
			dto.setReference_id(tr.getReference_id());
			dto.setTotalAmount(tr.getTotalAmount());
			dto.setTransaction_id(tr.getTransaction_id());
			
			h.setTransaction(dto);
			
			}
			list.add(h);
		}
		return list;
	}

}
