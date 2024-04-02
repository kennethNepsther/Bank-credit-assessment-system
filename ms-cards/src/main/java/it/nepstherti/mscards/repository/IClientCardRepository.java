package it.nepstherti.mscards.repository;

import it.nepstherti.mscards.model.ClientCardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IClientCardRepository extends JpaRepository<ClientCardModel, Long> {

    List<ClientCardModel> findByNif(String nif);

}
