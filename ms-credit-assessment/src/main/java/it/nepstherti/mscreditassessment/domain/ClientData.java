package it.nepstherti.mscreditassessment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientData {  // Dados do cliente
    private Long id;
    private String fullName;
    private Integer age;

}
