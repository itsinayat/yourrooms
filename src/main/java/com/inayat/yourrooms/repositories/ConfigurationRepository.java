package com.inayat.yourrooms.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.Configuration;
import com.inayat.yourrooms.entity.Role;
import com.inayat.yourrooms.entity.User;

@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, Long> {
	 @Query("SELECT c from Configuration c "
				+ "WHERE c.key = :key ")
		public Configuration findByKey(String key);
}
