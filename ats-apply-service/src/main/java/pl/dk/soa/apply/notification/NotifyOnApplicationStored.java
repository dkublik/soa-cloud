package pl.dk.soa.apply.notification;

import org.springframework.context.event.EventListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dk.soa.apply.store.ApplicationStoredEvent;
import pl.dk.soa.apply.store.StoredApplication;

import static pl.dk.soa.apply.notification.JmsConfig.DESTINATION_NAME;

@Service
class NotifyOnApplicationStored {

   //private static String PREFILL_ENDPOINT = "http://prefill-service/v1/prefill/for-candidate/{candidateId}";
   private static String PREFILL_ENDPOINT = "http://localhost:8081/v1/prefill/for-candidate/{candidateId}";

    private final JmsTemplate jmsTemplate;
    private final RestTemplate restTemplate;

    NotifyOnApplicationStored(JmsTemplate jmsTemplate, RestTemplate restTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.restTemplate = restTemplate;
    }

    @EventListener(classes = ApplicationStoredEvent.class)
    void onApplicationPersisted(ApplicationStoredEvent event) {
        Prefill prefillData = restTemplate.getForObject(PREFILL_ENDPOINT, Prefill.class, event.getSource().getCandidateId());
        assignPriority(event.getSource(), prefillData);
        jmsTemplate.convertAndSend(DESTINATION_NAME, new Notification(event.getSource(), prefillData));
    }

    private void assignPriority(StoredApplication application, Prefill prefillData) {
        if (prefillData.getYearOfExperience() < 5) {
            application.setPriority(StoredApplication.Priority.LOW);
        } else if (prefillData.getYearOfExperience() < 10) {
            application.setPriority(StoredApplication.Priority.MEDIUM);
        } else {
            application.setPriority(StoredApplication.Priority.HIGH);
        }
    }

}
