package it.nepstherti.mscards.service;

import it.nepstherti.mscards.model.ClientCardModel;

import java.util.List;

public interface IClientCardService {

    List<ClientCardModel> listCreditCardsByNif(String nif);

}
