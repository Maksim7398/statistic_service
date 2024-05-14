package com.transactions_categories.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Suggestions implements Serializable {

    private String value;

}
