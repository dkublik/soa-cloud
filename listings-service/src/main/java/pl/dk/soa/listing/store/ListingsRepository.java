package pl.dk.soa.listing.store;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparing;

@Service
public class ListingsRepository {

    private Map<String, StoredListing> listings = new HashMap<>();

    public void store(StoredListing listing) {
        listings.put(listing.getId(), listing);
        listing.stored();
    }

    public List<StoredListing> findAll() {
        List<StoredListing> allApplications = new ArrayList<>(listings.values());
        allApplications.sort(comparing(StoredListing::getCreatedTime));
        return allApplications;
    }

    public Optional<StoredListing> find(String id) {
        return Optional.ofNullable(listings.get(id));
    }

}
