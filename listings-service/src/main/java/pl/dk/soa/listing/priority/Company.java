package pl.dk.soa.listing.priority;

public class Company {

    private String id;
    private String name;
    private String memberStatus;

    public Company(String id, String name, String memberStatus) {
        this.id = id;
        this.name = name;
        this.memberStatus = memberStatus;
    }

    public Company() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

}

