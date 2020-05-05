package com.inayat.yourrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inayat.yourrooms.entity.Wallet;
import com.inayat.yourrooms.entity.WalletTransactions;

public interface WalletTransactionRepository extends CrudRepository<WalletTransactions, Long> {

	 @Query("SELECT wt from WalletTransactions wt "
				+ "WHERE wt.wallet = :wallet")
	List<WalletTransactions> findByWallet(Wallet wallet);
}
