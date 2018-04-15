package pl.dk.soa.listing.service;

import org.springframework.stereotype.Service;
import pl.dk.soa.listing.resourcev3.Listing;
import pl.dk.soa.listing.store.StoredListing;

@Service
class ListingConverter {

    StoredListing toStoredApplication(Listing listing) {
        StoredListing storedListing = new StoredListing();
        storedListing.setCompany(listing.getCompany());
        storedListing.setJobDescription(listing.getJobDescription());
        return storedListing;
    }

}
