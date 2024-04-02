package it.nepstherti.mscards.model;

import it.nepstherti.mscards.enums.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;




@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "credit_card")
@EqualsAndHashCode(of = "id")
public class CreditCardModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardName;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private BigDecimal income;
    private BigDecimal baseline;

}
