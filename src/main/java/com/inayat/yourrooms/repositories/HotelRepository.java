package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inayat.yourrooms.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	@Query("SELECT h from Hotel h " + "WHERE h.city = :city ORDER BY :sort  ASC")
	public List<Hotel> findByCityASC(String city,String sort);

	@Query("SELECT h from Hotel h " + "WHERE h.pincode = :pincode ORDER BY :sort ASC")
	public List<Hotel> findByPinCodeASC(String pincode,String sort);
	
	@Query("SELECT h from Hotel h " + "WHERE h.pincode = :pincode and  h.city = :city ORDER BY :sort ASC")
	public List<Hotel> findByPinCodeAndCityASC(String pincode,String city,String sort);
	
	
	@Query("SELECT h from Hotel h " + "WHERE h.city = :city ORDER BY :sort DESC")
	public List<Hotel> findByCityDESC(String city,String sort);

	@Query("SELECT h from Hotel h " + "WHERE h.pincode = :pincode ORDER BY :sort DESC")
	public List<Hotel> findByPinCodeDESC(String pincode,String sort);
	
	@Query("SELECT h from Hotel h " + "WHERE h.pincode = :pincode and  h.city = :city ORDER BY :sort DESC")
	public List<Hotel> findByPinCodeAndCityDESC(String pincode,String city,String sort);

}
