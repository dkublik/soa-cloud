package pl.dk.soa.listing.resourcev3;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.dk.soa.listing.store.StoredListing;

@Service
public class ListingsApprover {

    @Async
    public void approve(StoredListing listing) throws InterruptedException {
        Thread.sleep(2000);
        listing.accepted();
    }

}
