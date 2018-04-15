package pl.dk.soa.listing.resourcev3;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dk.soa.listing.service.ListingsService;
import pl.dk.soa.listing.store.StoredListing;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/listings", produces = APPLICATION_JSON_VALUE, consumes = "application/vnd.listings.v3+json; charset=UTF-8")
@Api(description = "listings v3")
class ListingsControllerV3 {

    private ListingsService listingsService;
    private final ListingsApprover listingsApprover;

    ListingsControllerV3(ListingsService listingsService, ListingsApprover listingsApprover) {
        this.listingsService = listingsService;
        this.listingsApprover = listingsApprover;
    }

    @PostMapping
    @ApiOperation(value = "create listing")
    ResponseEntity<ListingResponse> createListing(@RequestBody Listing listing) throws InterruptedException {
        StoredListing storedListing = listingsService.create(listing);
        listingsApprover.approve(storedListing);
        return new ResponseEntity<>(new ListingResponse(storedListing.getId(),
                storedListing.getPriority().toString()),
                ACCEPTED);
    }

    @GetMapping("{listingId}")
    @ApiOperation(value = "show listing")
    ResponseEntity<StoredListing> showListing(@PathVariable String listingId) {
        return listingsService.find(listingId)
                .map(data -> new ResponseEntity(data, OK))
                .orElse(new ResponseEntity(NOT_FOUND));
    }

    @GetMapping
    @ApiOperation(value = "show listings")
    List<StoredListing> showListings() {
        return listingsService.findAll();
    }

}
