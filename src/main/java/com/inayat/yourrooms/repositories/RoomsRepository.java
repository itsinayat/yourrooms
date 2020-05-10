package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.Hotel;
import com.inayat.yourrooms.entity.Room;

public interface RoomsRepository  extends CrudRepository<Room, Long>{
	@Query("SELECT r from Room r " + "WHERE r.hotel = :hotel_id ")
	List<Room> findAllByHotel(Hotel hotel_id);

}
