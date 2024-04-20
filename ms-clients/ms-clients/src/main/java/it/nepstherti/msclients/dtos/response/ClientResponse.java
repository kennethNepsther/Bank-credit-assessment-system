package it.nepstherti.msclients.dtos.response;

import it.nepstherti.msclients.model.ClientModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private Long id;
    private String fullName;
    private int age;
    private String nif;
    public ClientResponse(ClientModel clientModel) {
        this.id = clientModel.getId();
        this.fullName = clientModel.getFullName();
        this.age = calculateAge(clientModel.getBirthDate());
        this.nif = clientModel.getFullName();
    }

    public  int calculateAge(LocalDate birthDate) {
        LocalDate date = LocalDate.now();
        Period period = Period.between(birthDate, date);
        return period.getYears();


    }
}
