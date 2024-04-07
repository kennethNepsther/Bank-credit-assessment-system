package it.nepstherti.mscreditassessment.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCreditCard {
    private String cardName;
    private String cardType;
    private BigDecimal ApprovedBaseline;
}
