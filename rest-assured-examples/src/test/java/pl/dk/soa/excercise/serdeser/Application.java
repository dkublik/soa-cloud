package pl.dk.soa.excercise.serdeser;

public class Application {

    private String candidateId;
    private String messageToRecruiter;
    private String listingId;

    public Application(String candidateId, String messageToRecruiter, String listingId) {
        this.candidateId = candidateId;
        this.messageToRecruiter = messageToRecruiter;
        this.listingId = listingId;
    }

    public Application() {
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

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public void setMessageToRecruiter(String messageToRecruiter) {
        this.messageToRecruiter = messageToRecruiter;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }
}
