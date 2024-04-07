package it.nepstherti.mscards.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardRequestDetails {
    private Long idCard;
    private String nif;
    private String address;
    private BigDecimal baselineApproved;
}