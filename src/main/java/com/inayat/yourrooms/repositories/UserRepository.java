package com.inayat.yourrooms.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);

    @Query("SELECT u from User u "
			+ "WHERE u.referral_code = :referral_code ")
	public User findByReferral_code(String referral_code);
}
