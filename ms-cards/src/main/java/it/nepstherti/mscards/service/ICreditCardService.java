package it.nepstherti.mscards.service;

import it.nepstherti.mscards.model.CreditCardModel;

import java.util.List;

public interface ICreditCardService {
     CreditCardModel saveCreditCard(CreditCardModel card);
     List<CreditCardModel> getCreditCardsIncomeLessThanEquals(Long income);

}
