package pl.dk.soa.apply.domainevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
class DomainEventConfiguration {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void setupDomainEvents() {
        DomainEvents.setApplicationEventPublisher(applicationEventPublisher);
    }

}
