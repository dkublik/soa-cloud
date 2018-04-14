package pl.dk.soa.apply.notification;

public class Prefill {

    private String firstName;
    private String lastName;
    private String email;
    private int yearOfExperience;

    public Prefill(String firstName, String lastName, String email, int yearOfExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.yearOfExperience = yearOfExperience;
    }

    public Prefill() {
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
}

