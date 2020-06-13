package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.BookingTransaction;
import com.inayat.yourrooms.entity.Booking;

public interface BookingTransactionRepository extends CrudRepository<BookingTransaction, Long> {

	 @Query("SELECT bt from BookingTransaction bt "
				+ "WHERE bt.order_id = :order_id ")
	BookingTransaction findByOrderId(String order_id);

	 @Query("SELECT bt from BookingTransaction bt "
				+ "WHERE bt.booking = :bookings and bt.paymentHash= :ph")
	List<BookingTransaction> findByBookingIdAndPaymentHash(Booking bookings,String ph);
}
