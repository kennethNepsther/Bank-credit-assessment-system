package it.nepstherti.mscreditassessment.domain;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ClientCards { // Dados do cartão do client

    private String cardName;
    private String cardType;
    private BigDecimal baselineApproved;
}
