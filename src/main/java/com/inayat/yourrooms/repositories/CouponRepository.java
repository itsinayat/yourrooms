package com.inayat.yourrooms.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.Coupons;

@Repository
public interface CouponRepository extends CrudRepository<Coupons, Long> {
	 @Query("SELECT c from Coupons c "
				+ "WHERE c.code = :code ")
		public Coupons findBycode(String code);
}
