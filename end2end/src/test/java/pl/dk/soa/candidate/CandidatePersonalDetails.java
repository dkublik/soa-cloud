package pl.dk.soa.candidate;

public class CandidatePersonalDetails {

    private String login;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String photoPath;
    private String personalDescription;

    public CandidatePersonalDetails(String login, String name, String surname, String email, String phoneNumber, String photoPath, String personalDescription) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photoPath = photoPath;
        this.personalDescription = personalDescription;
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
}
