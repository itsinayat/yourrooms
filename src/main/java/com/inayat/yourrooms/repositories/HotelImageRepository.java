package com.inayat.yourrooms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.HotelImage;

public interface HotelImageRepository extends CrudRepository<HotelImage, Long>{
	
}
