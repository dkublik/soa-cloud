package pl.dk.soa.company;


public class Company {

    private String id;
    private String name;
    private String domain;
    private String email;
    private String vatIdentificationNumber;
    private String memberStatus;


    public Company(String id, String name, String domain, String email, String vatIdentificationNumber,
            String memberStatus) {
        this.id = id;
        this.name = name;
        this.domain = domain;
        this.email = email;
        this.vatIdentificationNumber = vatIdentificationNumber;
        this.memberStatus = memberStatus;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVatIdentificationNumber() {
        return vatIdentificationNumber;
    }

    public void setVatIdentificationNumber(String vatIdentificationNumber) {
        this.vatIdentificationNumber = vatIdentificationNumber;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
