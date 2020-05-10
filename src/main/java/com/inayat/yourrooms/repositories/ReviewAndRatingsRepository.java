package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.Hotel;
import com.inayat.yourrooms.entity.ReviewAndRating;

public interface ReviewAndRatingsRepository extends CrudRepository<ReviewAndRating, Long> {
	@Query("SELECT rr from ReviewAndRating rr " + "WHERE rr.create_user_id = :user and  rr.hotel = :hotel ")
	ReviewAndRating findByUserAndHotel(Long user, Hotel hotel);

	@Query("SELECT rr from ReviewAndRating rr " + "WHERE rr.hotel = :hotel")
	List<ReviewAndRating> findByHotel(Hotel hotel);
}
