package pl.dk.soa.listing.priority;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.dk.soa.listing.store.ListingStoredEvent;
import pl.dk.soa.listing.store.StoredListing;

@Service
class AssignPriorityOnListingStored {

    private final CompanyClient companyClient;

    AssignPriorityOnListingStored(CompanyClient companyClient) {
        this.companyClient = companyClient;
    }

    @EventListener(classes = ListingStoredEvent.class)
    void onApplicationPersisted(ListingStoredEvent event) {
        Company companyData = companyClient.getCompanyInfo(event.getSource());
        assignPriority(event.getSource(), companyData);
    }

    private void assignPriority(StoredListing application, Company companyData) {
        if (companyData.getMemberStatus().equals("PLATINIUM")) {
            application.setPriority(StoredListing.Priority.HIGH);
        } else if (companyData.getMemberStatus().equals("GOLD")) {
            application.setPriority(StoredListing.Priority.MEDIUM);
        } else {
            application.setPriority(StoredListing.Priority.LOW);
        }
    }

}
