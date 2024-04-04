package it.nepstherti.mscreditassessment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientStatus { // situação cliente
    private ClientData clientdata;
    private List<ClientCards> cardsList;

}
