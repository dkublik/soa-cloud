package pl.dk.soa.company.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import pl.dk.soa.company.Company;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CompanyService.class);

    private Map<String, Company> companies = new HashMap<>();

    public Optional<Company> getCompanyData(String companyId) {
        log.info("getting company data for {}", companyId);
        return Optional.ofNullable(companies.get(companyId.toLowerCase()));
    }

    public void add(String companyId, Company company) {
        companies.put(companyId.toLowerCase(), company);
    }

}
