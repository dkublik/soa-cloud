package pl.dk.soa.listing.priority;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dk.soa.listing.store.StoredListing;

@Service
public class CompanyClient {

    private static String COMPANY_ENDPOINT = "http://localhost:8091/company/{companyId}";

    private final RestTemplate restTemplate;

    public CompanyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Company getCompanyInfo(StoredListing listing) {
        return restTemplate.getForObject(COMPANY_ENDPOINT, Company.class, listing.getCompany());
    }

}
