package com.inayat.yourrooms.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.Booking;
import com.inayat.yourrooms.entity.Hotel;
import com.inayat.yourrooms.entity.User;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

	@Query("SELECT b from Booking b " + "WHERE b.user = :user")
	List<Booking> findByUser(User user);

	@Query("SELECT b from Booking b " + "WHERE b.user =:user and id =:bookingId")
	Booking findByUserAndBooking(User user, Long bookingId);

	@Query("SELECT b from Booking b WHERE b.hotel=:hotel")
	List<Booking> finByHotel(Hotel hotel);
	
	@Query("SELECT b from Booking b where b.checkinDate BETWEEN :checkinDate AND :checkoutDate AND b.hotel=:hotel")
	List<Booking> findAvailability(Date checkinDate, Date checkoutDate, Hotel hotel);
}
