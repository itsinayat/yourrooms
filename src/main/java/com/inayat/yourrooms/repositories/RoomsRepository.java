package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.Hotels;
import com.inayat.yourrooms.entity.Rooms;

public interface RoomsRepository  extends CrudRepository<Rooms, Long>{
	@Query("SELECT r from Rooms r " + "WHERE r.hotel = :hotel_id ")
	List<Rooms> findAllByHotel(Hotels hotel_id);

}
