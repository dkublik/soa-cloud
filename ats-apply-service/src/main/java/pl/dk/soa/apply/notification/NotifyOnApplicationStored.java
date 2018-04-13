package pl.dk.soa.apply.notification;

import org.springframework.context.event.EventListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dk.soa.apply.store.ApplicationStoredEvent;

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
        jmsTemplate.convertAndSend(DESTINATION_NAME, new Notification(event.getSource(), prefillData));
    }

}
