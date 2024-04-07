package it.nepstherti.mscards.model.dto;

import it.nepstherti.mscards.model.ClientCardModel;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ClientCardResponse {
    private String cardName;
    private String creditCard;
    private String cardType;
    private BigDecimal baselineApproved;



    public ClientCardResponse(ClientCardModel clientCard) {
        this.cardName = clientCard.getCreditCard().getCardName();
        this.cardType = clientCard.getCreditCard().getCardType().toString();
        this.baselineApproved = clientCard.getBaselineApproved();

    }
}


