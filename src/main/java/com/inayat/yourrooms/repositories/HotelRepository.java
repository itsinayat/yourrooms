package com.inayat.yourrooms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.Hotels;

public interface HotelRepository extends CrudRepository<Hotels, Long> {

}
