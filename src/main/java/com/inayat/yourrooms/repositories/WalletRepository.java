package com.inayat.yourrooms.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inayat.yourrooms.entity.Wallet;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
