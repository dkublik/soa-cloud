package pl.dk.soa.apply.store;

import pl.dk.soa.apply.domainevent.DomainEvent;

public class ApplicationStoredEvent extends DomainEvent {

    public ApplicationStoredEvent(StoredApplication source) {
        super(source);
    }

    @Override
    public StoredApplication getSource() {
        return (StoredApplication) super.getSource();
    }
}
