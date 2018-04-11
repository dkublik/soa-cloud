package pl.dk.soa.candidate.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dk.soa.candidate.service.CandidatePersonalDetails;
import pl.dk.soa.candidate.service.CandidateService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/v1/candidates/profile", produces = APPLICATION_JSON_VALUE)
@Api(description = "candidate data")
class CandidateControllerV1 {

    private final CandidateService candidateService;

    CandidateControllerV1(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    @ApiOperation(value = "get candidate data")
    @SuppressWarnings("unchecked")
    CandidatePersonalDetails getCandidate() {
        return candidateService.getCandidate("mhamill").get();
    }
}
