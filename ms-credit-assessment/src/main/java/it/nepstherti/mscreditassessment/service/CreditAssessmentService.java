package it.nepstherti.mscreditassessment.service;

import it.nepstherti.mscreditassessment.domain.ClientEvaluationResponse;
import it.nepstherti.mscreditassessment.domain.ClientStatus;



public interface CreditAssessmentService {
    ClientStatus getClientStatus(String nif);
    ClientEvaluationResponse getClientEvaluation(String nif, Long income);
}
