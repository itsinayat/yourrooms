package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.Hotels;
import com.inayat.yourrooms.entity.ReviewAndRatings;

public interface ReviewAndRatingsRepository extends CrudRepository<ReviewAndRatings, Long> {
	@Query("SELECT rr from ReviewAndRatings rr " + "WHERE rr.create_user_id = :user and  rr.hotel = :hotel ")
	ReviewAndRatings findByUserAndHotel(Long user, Hotels hotel);

	@Query("SELECT rr from ReviewAndRatings rr " + "WHERE rr.hotel = :hotel")
	List<ReviewAndRatings> findByHotel(Hotels hotel);
}
