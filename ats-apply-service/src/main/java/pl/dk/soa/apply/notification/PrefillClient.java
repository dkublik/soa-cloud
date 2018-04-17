package pl.dk.soa.apply.notification;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import pl.dk.soa.apply.store.StoredApplication;

import java.io.IOException;

@Service
public class PrefillClient {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PrefillClient.class);

    //private static String PREFILL_ENDPOINT = "http://prefill-service/v1/prefill/for-candidate/{candidateId}";
    private static String PREFILL_ENDPOINT = "http://localhost:8081/v1/prefill/for-candidate/{candidateId}";

    private final RestTemplate restTemplate;

    public PrefillClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        restTemplateShouldWorkInTheStubMode();
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Prefill getPrefillData(StoredApplication application) {
        log.info("calling prefill for application {} ", application.getId());
        return restTemplate.getForObject(PREFILL_ENDPOINT, Prefill.class, application.getCandidateId());
    }

    public Prefill fallback(StoredApplication application) {
        log.info("prefill not available - getting info from fallback");
        return new Prefill("FallbackName", "FallbackSurname", "FallbackEmail", 0);
    }

    private void restTemplateShouldWorkInTheStubMode() {
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                if (response.getStatusCode().value() == 501) {
                    return false;
                }
                return super.hasError(response);
            }
        });
    }

}
