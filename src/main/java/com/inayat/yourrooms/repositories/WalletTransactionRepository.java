package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.Wallet;
import com.inayat.yourrooms.entity.WalletTransaction;

public interface WalletTransactionRepository extends CrudRepository<WalletTransaction, Long> {

	 @Query("SELECT wt from WalletTransaction wt "
				+ "WHERE wt.wallet = :wallet")
	List<WalletTransaction> findByWallet(Wallet wallet);
}
