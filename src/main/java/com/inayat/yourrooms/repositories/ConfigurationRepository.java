package com.inayat.yourrooms.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.Configuration;

@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, Long> {
	 @Query("SELECT c from Configuration c "
				+ "WHERE c.key = :key ")
		public Configuration findByKey(String key);


	  @Transactional
	  @Modifying
	 @Query("DELETE from Configuration c "
				+ "WHERE c.key = :key ")
	public void deleteByKey(String key);
}
