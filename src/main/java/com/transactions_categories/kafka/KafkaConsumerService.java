package com.transactions_categories.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactions_categories.model.TransactionMessage;
import com.transactions_categories.service.TransactionsPaymentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final TransactionsPaymentsService service;

    @KafkaListener(topics = "transaction_topic",containerFactory = "kafkaListenerContainerFactoryStringTransactionMessage")
    public void readMessage(String message){

        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            TransactionMessage transactionMessage = objectMapper.readValue(message, TransactionMessage.class);
            service.createTransactionsPayments(transactionMessage);
            log.info("MESSAGE READING: {}",transactionMessage);
        } catch (JsonProcessingException e) {
            log.error("MESSAGE NOT READING");
            throw new RuntimeException(e);
        }
    }
}
