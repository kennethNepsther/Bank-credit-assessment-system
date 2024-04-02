package it.nepstherti.mscards.dto;

import it.nepstherti.mscards.model.ClientCardModel;
import it.nepstherti.mscards.model.CreditCardModel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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


