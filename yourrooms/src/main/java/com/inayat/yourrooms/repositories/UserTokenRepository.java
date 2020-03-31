package com.inayat.yourrooms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.UserToken;


@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
	
	public UserToken findUserTokenByTokenKey(String tokenKey);
	
	@Query("SELECT ut FROM UserToken ut, User u "
			+ "WHERE ut.user.id = u.id "
			+ "AND u.username = :username "
			+ "AND ut.tokenKey = :token "
			+ "AND status = :userTokenStatus")
	UserToken checkUserToken(@Param("token") String token, @Param("username") String username, @Param("userTokenStatus") String userTokenStatus);

	@Query("SELECT ut FROM UserToken ut, User u "
			+ "WHERE ut.user.id = u.id "
			+ "AND u.username = :username "
			+ "AND status = :userTokenStatus")
	public UserToken findUserTokenByUsername(@Param("username") String username, @Param("userTokenStatus") String userTokenStatus);
}
