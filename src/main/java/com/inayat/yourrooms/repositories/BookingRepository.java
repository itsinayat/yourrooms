package com.inayat.yourrooms.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.Bookings;

@Repository
public interface BookingRepository extends CrudRepository<Bookings, Long> {
}
