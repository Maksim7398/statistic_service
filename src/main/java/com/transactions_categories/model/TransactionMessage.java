package com.transactions_categories.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionMessage {

    private UUID userIdSource;

    private UUID accountIdSource;

    private BigDecimal totalSum;

    private String okvedCode;

    private UUID userIdDestination;

    private UUID accountIdDestination;

    private Integer bikDestination;

    private Integer bikSource;

}
