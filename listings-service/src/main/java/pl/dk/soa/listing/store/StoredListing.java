package pl.dk.soa.listing.store;

import pl.dk.soa.listing.domainevent.DomainEvents;

import java.time.Instant;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;
import static pl.dk.soa.listing.store.StoredListing.Status.*;

public class StoredListing {

    public enum Status {
        NEW,
        ACCEPTED,
        REJECTED
    }

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    private String id = randomUUID().toString();
    private String company;
    private String jobDescription;
    private Priority priority;
    private Instant createdTime = now();
    private Status status = NEW;

    public void stored() {
        DomainEvents.publish(new ListingStoredEvent(this));
    }

    public void accepted() {
        status = ACCEPTED;
    }

    public void rejected() {
        status = REJECTED;
    }

    public String getId() {
        return this.id;
    }

    public Instant getCreatedTime() {
        return this.createdTime;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

}
