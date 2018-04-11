package pl.dk.soa.apply.store;

import pl.dk.soa.apply.domainevent.DomainEvents;

import java.time.Instant;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;
import static pl.dk.soa.apply.store.StoredApplication.Status.*;

public class StoredApplication {

    public enum Status {
        NEW,
        ACCEPTED,
        REJECTED
    }

    private String id = randomUUID().toString();
    private String candidateId;
    private String messageToRecruiter;
    private String listingId;
    private Instant createdTime = now();
    private Status status = NEW;

    public void stored() {
        DomainEvents.publish(new ApplicationStoredEvent(this));
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

    public String getCandidateId() {
        return this.candidateId;
    }

    public String getMessageToRecruiter() {
        return this.messageToRecruiter;
    }

    public String getListingId() {
        return this.listingId;
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

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public void setMessageToRecruiter(String messageToRecruiter) {
        this.messageToRecruiter = messageToRecruiter;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
