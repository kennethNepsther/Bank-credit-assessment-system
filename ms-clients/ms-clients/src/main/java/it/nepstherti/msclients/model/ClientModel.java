package it.nepstherti.msclients.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
public class ClientModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    private LocalDate birthDate;
    private String nif;
}
