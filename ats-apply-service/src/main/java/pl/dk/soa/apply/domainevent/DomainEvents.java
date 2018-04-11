package pl.dk.soa.apply.domainevent;

import org.slf4j.Logger;
import org.springframework.context.ApplicationEventPublisher;

public final class DomainEvents {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DomainEvents.class);
    private static ApplicationEventPublisher applicationEventPublisher;

    private DomainEvents() {
    }

    public static void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
        log.trace("Published: {}", event);
    }

    public static void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        DomainEvents.applicationEventPublisher = applicationEventPublisher;
    }
}
