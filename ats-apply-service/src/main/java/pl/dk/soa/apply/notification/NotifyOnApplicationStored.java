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

    private final JmsTemplate jmsTemplate;
    private final PrefillClient prefillClient;

    NotifyOnApplicationStored(JmsTemplate jmsTemplate, PrefillClient prefillClient) {
        this.jmsTemplate = jmsTemplate;
        this.prefillClient = prefillClient;
    }

    @EventListener(classes = ApplicationStoredEvent.class)
    void onApplicationPersisted(ApplicationStoredEvent event) {
        Prefill prefillData = prefillClient.getPrefillData(event.getSource());
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
