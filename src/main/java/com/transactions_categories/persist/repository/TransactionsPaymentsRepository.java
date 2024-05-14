package com.transactions_categories.persist.repository;

import com.transactions_categories.persist.entity.TransactionPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionsPaymentsRepository extends JpaRepository<TransactionPayments, UUID> {
}
