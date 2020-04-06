package com.inayat.yourrooms.repositories;

import org.springframework.data.repository.CrudRepository;
import com.inayat.yourrooms.entity.WalletTransactions;

public interface WalletTransactionRepository extends CrudRepository<WalletTransactions, Long> {

}
