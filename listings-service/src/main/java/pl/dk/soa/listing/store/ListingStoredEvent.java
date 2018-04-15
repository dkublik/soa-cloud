package pl.dk.soa.listing.store;

import pl.dk.soa.listing.domainevent.DomainEvent;

public class ListingStoredEvent extends DomainEvent {

    public ListingStoredEvent(StoredListing source) {
        super(source);
    }

    @Override
    public StoredListing getSource() {
        return (StoredListing) super.getSource();
    }
}
