package it.nepstherti.mscreditassessment.controller;

import it.nepstherti.mscreditassessment.domain.ClientEvaluationResponse;
import it.nepstherti.mscreditassessment.domain.ClientStatus;
import it.nepstherti.mscreditassessment.domain.EvaluationData;
import it.nepstherti.mscreditassessment.service.CreditAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-assessment")
@RequiredArgsConstructor
public class AssessmentController {
    final CreditAssessmentService assessmentService;


    @GetMapping(value = "/client-status", params = "nif")
    public ResponseEntity<ClientStatus> clientStatus(@RequestParam("nif") String nif)  {
        ClientStatus clientStatusResponse = assessmentService.getClientStatus(nif);
        return ResponseEntity.ok(clientStatusResponse);

    }
@PostMapping
    public ResponseEntity clientEvaluation(@RequestBody EvaluationData evaluationData){
        ClientEvaluationResponse clientEvaluationResponse = assessmentService.getClientEvaluation(evaluationData.getNif(),
                evaluationData.getIncome());
        return ResponseEntity.ok(clientEvaluationResponse);


    }
}
