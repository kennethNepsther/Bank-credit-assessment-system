package it.nepstherti.mscreditassessment.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditCard {
    private Long id;
    private String cardName;
    private String cardType;
    private BigDecimal baseline;
}
