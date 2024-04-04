package it.nepstherti.msclients.exceptions.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {
    private Long timestamp;
    private Integer statusCode;
    private String errorMessage;
}
