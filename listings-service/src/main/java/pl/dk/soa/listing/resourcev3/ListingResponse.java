package pl.dk.soa.listing.resourcev3;

public class ListingResponse {

    private String listingId;
    private String priority;

    public ListingResponse(String listingId, String priority) {
        this.listingId = listingId;
        this.priority = priority;
    }

    public ListingResponse() {
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
