package it.nepstherti.msclients.repository;

import it.nepstherti.msclients.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<ClientModel, Long> {
    Optional<ClientModel> findClientModelByNif(String nif);
    void deleteByNif(String nif);
}
