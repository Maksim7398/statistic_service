package com.transactions_categories.service;

import com.transactions_categories.client.OKVEDClient;
import com.transactions_categories.client.model.RequestQuery;
import com.transactions_categories.client.model.Suggestions;
import com.transactions_categories.model.TransactionMessage;
import com.transactions_categories.persist.entity.TransactionPayments;
import com.transactions_categories.persist.repository.TransactionsPaymentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionsPaymentsService {

    private final TransactionsPaymentsRepository repository;

    private final OKVEDClient client;

    @Transactional
    public void createTransactionsPayments(final TransactionMessage transactionMessage) {
        final TransactionPayments transactionPayments = new TransactionPayments();

        transactionPayments.setInternalTransfer(transactionMessage.getBikSource().equals(transactionMessage.getBikDestination()));
        transactionPayments.setCategory(getCategory(transactionMessage.getOkvedCode()));
        transactionPayments.setTotalSum(transactionMessage.getTotalSum());
        transactionPayments.setBikDestination(transactionMessage.getBikDestination());
        transactionPayments.setAccountIdSource(transactionMessage.getAccountIdSource());
        transactionPayments.setAccountIdDestination(transactionMessage.getAccountIdDestination());
        transactionPayments.setUserIdSource(transactionMessage.getUserIdSource());
        transactionPayments.setUserIdDestination(transactionMessage.getUserIdDestination());
        transactionPayments.setCreateDate(transactionMessage.getCreateDate());

        repository.save(transactionPayments);
        log.info("Transaction payments saved");
    }

    private String getCategory(String code) {
        if (code == null) {
            return "ВНУТРИБАНКОВСКИЙ ПЕРЕВОД";
        }
        System.out.println(code);
        Set<Suggestions> categories = client.getTypeForCode(new RequestQuery(code));
        return categories.stream().findFirst().orElseThrow(() -> new RuntimeException("Не удалось получить категорию")).getValue();
    }

}
