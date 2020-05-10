package com.inayat.yourrooms.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.Coupon;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {
	 @Query("SELECT c from Coupon c "
				+ "WHERE c.code = :code ")
		public Coupon findBycode(String code);
}
