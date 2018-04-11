package pl.dk.soa.candidate.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dk.soa.candidate.service.CandidatePersonalDetails;
import pl.dk.soa.candidate.service.CandidateService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/v0/candidates/profile", produces = APPLICATION_JSON_VALUE)
@Api(description = "candidate data")
class CandidateControllerV0 {

    private final CandidateService candidateService;

    CandidateControllerV0(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    @ApiOperation(value = "get all candidates data")
    @SuppressWarnings("unchecked")
    CandidatePersonalDetailsCollection getCandidates() {
        return new CandidatePersonalDetailsCollection(candidateService.getCandidates());
    }

    @GetMapping("{candidateId}")
    @ApiOperation(value = "get candidate data")
    @SuppressWarnings("unchecked")
    ResponseEntity<CandidatePersonalDetails> getCandidate(@PathVariable String candidateId, HttpServletResponse httResponse) throws InterruptedException {
        httResponse.addCookie(new Cookie("trackingCallTime", System.currentTimeMillis() + ""));
        HttpHeaders headers = new HttpHeaders();
        headers.add("cookie-tracking-mode", "on");
        Thread.sleep(500);
        return candidateService.getCandidate(candidateId)
                .map(data -> new ResponseEntity(data, headers, OK))
                .orElse(new ResponseEntity(NOT_FOUND));
    }
}
