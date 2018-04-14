package pl.dk.soa.prefill.add;

import pl.dk.soa.prefill.validator.Pesel;

import javax.validation.constraints.NotNull;

public class NewPrefill {

    @NotNull private String firstName;
    @NotNull private String lastName;
    @NotNull private String email;
    @NotNull private int yearOfExperience;
    @NotNull private String passportNumber;
    @NotNull private String dateOfBirth;
    @NotNull @Pesel private String nationalIdentificationNumber;
    @NotNull private String phone;
    @NotNull private Address address;

    public NewPrefill(String firstName, String lastName, String email, int yearOfExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.yearOfExperience = yearOfExperience;
    }

    public NewPrefill() {
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

    public int getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(int yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalIdentificationNumber() {
        return nationalIdentificationNumber;
    }

    public void setNationalIdentificationNumber(String nationalIdentificationNumber) {
        this.nationalIdentificationNumber = nationalIdentificationNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
