package it.nepstherti.msclients.dtos.request;



import it.nepstherti.msclients.model.ClientModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public record ClientRequest(
        Long id,
        @NotNull(message = "Deve inserir o nome completo")
        String fullName,
        @NotNull(message = "Deve inserir a data de nascimento ")
        LocalDate birthDate,
        @NotNull(message = "Deve inserir o NIF")
        String nif
){
        public ClientModel createClient(){
                return new ClientModel()
                        .setFullName(this.fullName)
                        .setBirthDate(this.birthDate)
                        .setNif(this.nif);
        }

        public static void updateClient(ClientRequest clientRequest, ClientModel clientModel){
                BeanUtils.copyProperties(clientRequest, clientModel);
                clientModel.setId(clientRequest.id());

        }


}

