package it.nepstherti.mscreditassessment.controller;

import it.nepstherti.mscreditassessment.domain.ClientStatus;
import it.nepstherti.mscreditassessment.exception.MicroserviceErrorConnectionException;
import it.nepstherti.mscreditassessment.service.CreditAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-assessment")
@RequiredArgsConstructor
public class AssessmentController {
    final CreditAssessmentService assessmentService;
   @GetMapping
    public String index() {
        return " ok";
    }

    @GetMapping(value = "/client-status", params = "nif")
    public ResponseEntity<ClientStatus> clientStatus(@RequestParam("nif") String nif)  {
        ClientStatus clientStatusResponse = assessmentService.getClientStatus(nif);

        return ResponseEntity.ok(clientStatusResponse);


    }
}
