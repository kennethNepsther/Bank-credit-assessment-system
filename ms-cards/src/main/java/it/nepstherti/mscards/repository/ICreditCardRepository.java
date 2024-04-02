package it.nepstherti.mscards.repository;

import it.nepstherti.mscards.model.CreditCardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ICreditCardRepository extends JpaRepository<CreditCardModel, Long> {
    List<CreditCardModel> findCreditCardModelsByIncomeLessThanEqual(BigDecimal amount);
}
