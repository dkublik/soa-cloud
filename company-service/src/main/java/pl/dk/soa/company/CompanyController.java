package pl.dk.soa.company;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dk.soa.company.add.NewCompany;
import pl.dk.soa.company.service.CompanyService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/company", produces = APPLICATION_JSON_VALUE)
@Api(description = "info about companies")
class CompanyController {

    private final CompanyService companyService;

    CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("{companyId}")
    @ApiOperation(value = "get company data")
    @SuppressWarnings("unchecked")
    ResponseEntity<Company> getCompanyData(@PathVariable String companyId) {
        return companyService.getCompanyData(companyId)
                .map(data -> new ResponseEntity(data, OK))
                .orElse(new ResponseEntity(NOT_FOUND));
    }

    @PutMapping(value = "{companyId}", consumes = "application/vnd.company.v3+json")
    @ApiOperation(value = "add company data")
    ResponseEntity<Void> addCompany(@PathVariable String companyId, @RequestBody @Valid NewCompany newCompany) {
        companyService.add(companyId, new Company(companyId, newCompany.getName(), newCompany.getDomain(),
                newCompany.getEmail(), newCompany.getVatIdentificationNumber(), newCompany.getMemberStatus()));
        return new ResponseEntity<>(ACCEPTED);
    }

}
