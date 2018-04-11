package pl.dk.soa.apply.notification;

import pl.dk.soa.apply.store.StoredApplication;

import java.time.Instant;

public class Notification {

    private String applicationId;
    private String candidateId;
    private String firstName;
    private String lastName;
    private String email;
    private String messageToRecruiter;
    private String listingId;
    private Instant applicationDate;

    Notification(StoredApplication application, Prefill additionalData) {
        applicationId = application.getId();
        applicationDate = application.getCreatedTime();
        candidateId = application.getCandidateId();
        listingId = application.getListingId();
        messageToRecruiter = application.getMessageToRecruiter();
        firstName = additionalData.getFirstName();
        lastName = additionalData.getLastName();
        email = additionalData.getEmail();
    }

    public Notification() {
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getCandidateId() {
        return this.candidateId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMessageToRecruiter() {
        return this.messageToRecruiter;
    }

    public String getListingId() {
        return this.listingId;
    }

    public Instant getApplicationDate() {
        return this.applicationDate;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessageToRecruiter(String messageToRecruiter) {
        this.messageToRecruiter = messageToRecruiter;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public void setApplicationDate(Instant applicationDate) {
        this.applicationDate = applicationDate;
    }
}
