package it.nepstherti.mscards.service.serviceimpl;

import it.nepstherti.mscards.model.CreditCardModel;
import it.nepstherti.mscards.repository.ICreditCardRepository;
import it.nepstherti.mscards.service.ICreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements ICreditCardService {
    final ICreditCardRepository creditCardRepository;
    @Override
    @Transactional
    public CreditCardModel saveCreditCard(CreditCardModel card) {
        return creditCardRepository.save(card);
    }

    @Override
    public List<CreditCardModel> getCreditCardsIncomeLessThanEquals(Long income) {
        BigDecimal amount = BigDecimal.valueOf(income);
        return creditCardRepository.findCreditCardModelsByIncomeLessThanEqual(amount);
    }
}
