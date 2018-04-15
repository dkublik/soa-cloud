package pl.dk.soa.company.add;

import pl.dk.soa.company.validator.Nip;

import javax.validation.constraints.NotNull;

public class NewCompany {

    @NotNull private String name;
    @NotNull private String domain;
    @NotNull private String email;
    @NotNull @Nip private String vatIdentificationNumber;
    @NotNull private String memberStatus;


    public NewCompany(@NotNull String name, @NotNull String domain, @NotNull String email, @NotNull String vatIdentificationNumber,
            @NotNull String memberStatus) {
        this.name = name;
        this.domain = domain;
        this.email = email;
        this.vatIdentificationNumber = vatIdentificationNumber;
        this.memberStatus = memberStatus;
    }

    public NewCompany() {
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

}
