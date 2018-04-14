package pl.dk.soa.prefill;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dk.soa.prefill.add.NewPrefill;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/v1/prefill/for-candidate/", produces = APPLICATION_JSON_VALUE)
@Api(description = "pre filling data")
class PrefillController {

    private Map<String, Prefill> prefillData = new HashMap<>();

    @GetMapping("{candidateId}")
    @ApiOperation(value = "get prefill data")
    @SuppressWarnings("unchecked")
    ResponseEntity<Prefill> getPrefillData(@PathVariable String candidateId) {
        return Optional.ofNullable(prefillData.get(candidateId.toLowerCase()))
                .map(data -> new ResponseEntity(data, OK))
                .orElse(new ResponseEntity(NOT_FOUND));
    }

    @PutMapping("{candidateId}")
    @ApiOperation(value = "add candidate for prefill")
    ResponseEntity<Void> addPrefill(@PathVariable String candidateId, @RequestBody @Valid NewPrefill newPrefill) {
        prefillData.put(candidateId, new Prefill(newPrefill.getFirstName(), newPrefill.getLastName(), newPrefill.getEmail(), newPrefill.getYearOfExperience()));
        return new ResponseEntity<>(ACCEPTED);
    }

    @PostConstruct
    void fillSomeData() {
        prefillData.put("mhamill", new Prefill("Mark", "Hammil", "mark.hamill@gmail.com", 5));
        prefillData.put("just_britney", new Prefill("Britney", "Spears", "just_britney@spears.pl", 1));
        prefillData.put("mrpresident", new Prefill("Barack", "Obama", "mrPresident@wp.pl", 9));
    }

}
