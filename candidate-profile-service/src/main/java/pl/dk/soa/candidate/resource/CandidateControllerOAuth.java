package pl.dk.soa.candidate.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dk.soa.candidate.service.CandidatePersonalDetails;
import pl.dk.soa.candidate.service.CandidateService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/voauth/candidates/profile", produces = APPLICATION_JSON_VALUE)
@Api(description = "candidate data")
class CandidateControllerOAuth {

    private final CandidateService candidateService;

    CandidateControllerOAuth(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    @ApiOperation(value = "get candidate data")
    @SuppressWarnings("unchecked")
    ResponseEntity<CandidatePersonalDetails> getCandidate(HttpServletRequest request) {
        String authHeader = request.getHeader("authorization");
        if (authHeader == null) {
            return new ResponseEntity(UNAUTHORIZED);
        }
        if (!authHeader.equals("Bearer 1111-1111-1111")) {
            return new ResponseEntity(FORBIDDEN);
        }
        return new ResponseEntity(candidateService.getCandidate("mhamill").get(), OK);
    }
}
