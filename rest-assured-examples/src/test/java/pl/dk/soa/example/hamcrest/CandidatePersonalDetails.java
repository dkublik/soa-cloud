package pl.dk.soa.example.hamcrest;

import java.util.List;

public class CandidatePersonalDetails {

    private String login;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String photoPath;
    private String personalDescription;
    private int applicationsPosted;
    private double lastSalaryRequested;
    private List<Integer> avaialbleAtDays;
    private CandidateAddress address;

    public CandidatePersonalDetails(String login, String name, String surname, String email, String phoneNumber,
            String photoPath, String personalDescription, int applicationsPosted,
            double lastSalaryRequested, List<Integer> avaialbleAtDays, CandidateAddress address) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photoPath = photoPath;
        this.personalDescription = personalDescription;
        this.applicationsPosted = applicationsPosted;
        this.lastSalaryRequested = lastSalaryRequested;
        this.avaialbleAtDays = avaialbleAtDays;
        this.address = address;
    }

    public CandidatePersonalDetails() {
    }

    public String getLogin() {
        return this.login;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPhotoPath() {
        return this.photoPath;
    }

    public String getPersonalDescription() {
        return this.personalDescription;
    }

    public CandidateAddress getAddress() {
        return this.address;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    public void setAddress(CandidateAddress address) {
        this.address = address;
    }

    public double getLastSalaryRequested() {
        return lastSalaryRequested;
    }

    public void setLastSalaryRequested(double lastSalaryRequested) {
        this.lastSalaryRequested = lastSalaryRequested;
    }

    public int getApplicationsPosted() {
        return applicationsPosted;
    }

    public void setApplicationsPosted(int applicationsPosted) {
        this.applicationsPosted = applicationsPosted;
    }

    public List<Integer> getAvaialbleAtDays() {
        return avaialbleAtDays;
    }

    public void setAvaialbleAtDays(List<Integer> avaialbleAtDays) {
        this.avaialbleAtDays = avaialbleAtDays;
    }
}
