package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.Hotels;

public interface HotelRepository extends CrudRepository<Hotels, Long> {
	@Query("SELECT h from Hotels h " + "WHERE h.city = :city ")
	public List<Hotels> findByCity(String city);

	@Query("SELECT h from Hotels h " + "WHERE h.pincode = :pincode ")
	public List<Hotels> findByPinCode(String pincode);
	
	@Query("SELECT h from Hotels h " + "WHERE h.pincode = :pincode and  h.city = :city ")
	public List<Hotels> findByPinCodeAndCity(String pincode,String city);

}
