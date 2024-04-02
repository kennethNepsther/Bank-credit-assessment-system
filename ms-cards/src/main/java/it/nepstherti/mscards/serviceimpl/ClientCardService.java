package it.nepstherti.mscards.serviceimpl;

import it.nepstherti.mscards.model.ClientCardModel;
import it.nepstherti.mscards.repository.IClientCardRepository;
import it.nepstherti.mscards.service.IClientCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ClientCardService implements IClientCardService {

    final IClientCardRepository clientCardRepository;
    @Override
    public List<ClientCardModel> listCreditCardsByNif(String nif) {
        return clientCardRepository.findByNif(nif);
    }
}
