package it.nepstherti.mscards.model.dto;


import it.nepstherti.mscards.enums.CardType;
import it.nepstherti.mscards.model.CreditCardModel;

import java.math.BigDecimal;
public record CreditCardSaveRequest(Long id, String cardName, CardType cardType, BigDecimal income, BigDecimal baseline) {

    public CreditCardModel saveCard(){
        return new CreditCardModel()
                .setCardName(this.cardName)
                .setCardType(this.cardType)
                .setIncome(this.income)
                .setBaseline(this.baseline);
    }


}









