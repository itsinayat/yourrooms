package com.inayat.yourrooms.translator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inayat.yourrooms.dto.BookingHistoryResponse;
import com.inayat.yourrooms.dto.BookingTransactionDTO;
import com.inayat.yourrooms.entity.Booking;
import com.inayat.yourrooms.entity.BookingTransaction;
import com.inayat.yourrooms.entity.Hotel;
import com.inayat.yourrooms.repositories.HotelRepository;
@Service
public class BookingHistoryResponseTranslator {

	public List<BookingHistoryResponse> translate(List<Booking> bookings) throws ParseException {
		List<BookingHistoryResponse> list = new ArrayList<>();
		for (Booking b : bookings) {
			BookingHistoryResponse h = new BookingHistoryResponse();
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
			h.setCheckin_status(b.getCheckinStatus());
			h.setCheckout_status(b.getCheckoutStatus());
			h.setHotel(b.getHotel());

			BookingTransactionDTO dto = new BookingTransactionDTO();
			BookingTransaction tr = b.getTransaction();
			if (tr != null) {
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
