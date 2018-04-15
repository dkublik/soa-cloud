package pl.dk.soa.listing.resourcev3;

public class Listing {

    private String company;
    private String jobDescription;

    public Listing(String company, String jobDescription) {
        this.company = company;
        this.jobDescription = jobDescription;
    }

    public Listing() {
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
