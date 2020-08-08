package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
	@Query("SELECT h from Hotel h " + "WHERE h.city = :city ")
	public List<Hotel> findByCity(String city);

	@Query("SELECT h from Hotel h " + "WHERE h.pincode = :pincode ")
	public List<Hotel> findByPinCode(String pincode);
	
	@Query("SELECT h from Hotel h " + "WHERE h.pincode = :pincode and  h.city = :city ")
	public List<Hotel> findByPinCodeAndCity(String pincode,String city);

}
