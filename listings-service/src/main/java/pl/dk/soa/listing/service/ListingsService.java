package pl.dk.soa.listing.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import pl.dk.soa.listing.resourcev3.Listing;
import pl.dk.soa.listing.store.ListingsRepository;
import pl.dk.soa.listing.store.StoredListing;

import java.util.List;
import java.util.Optional;

@Service
public class ListingsService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ListingsService.class);

    private final ListingsRepository listingsRepository;

    private final ListingConverter listingConverter;

    ListingsService(ListingsRepository listingsRepository, ListingConverter listingConverter) {
        this.listingsRepository = listingsRepository;
        this.listingConverter = listingConverter;
    }

    public StoredListing create(Listing listing) {
        log.info("listing received for company {}", listing.getCompany());
        StoredListing storedListing = listingConverter.toStoredApplication(listing);
        listingsRepository.store(storedListing);
        return storedListing;
    }

    public List<StoredListing> findAll() {
        return listingsRepository.findAll();
    }

    public Optional<StoredListing> find(String id) {
        return listingsRepository.find(id);
    }

}
