package it.nepstherti.mscreditassessment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ClientEvaluationResponse {

    private List<ApprovedCreditCard> approvedCreditCardList;
}
