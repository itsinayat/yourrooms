package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.Bookings;
import com.inayat.yourrooms.entity.User;

@Repository
public interface BookingRepository extends CrudRepository<Bookings, Long> {

	@Query("SELECT b from Bookings b " + "WHERE b.user = :user")
	List<Bookings> findByUser(User user);

	@Query("SELECT b from Bookings b " + "WHERE b.user = :user and id =:bookingId")
	Bookings findByUserAndBooking(User user, Long bookingId);
}
