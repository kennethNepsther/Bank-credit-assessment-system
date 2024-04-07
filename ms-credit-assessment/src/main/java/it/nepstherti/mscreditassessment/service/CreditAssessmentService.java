package it.nepstherti.mscreditassessment.service;

import it.nepstherti.mscreditassessment.domain.CardRequestDetails;
import it.nepstherti.mscreditassessment.domain.ClientEvaluationResponse;
import it.nepstherti.mscreditassessment.domain.ClientStatus;
import it.nepstherti.mscreditassessment.domain.IssueProtocolCard;



public interface CreditAssessmentService {
    ClientStatus getClientStatus(String nif);
    ClientEvaluationResponse getClientEvaluation(String nif, Long income);
     IssueProtocolCard cardRequestIssuance(CardRequestDetails details);
}
