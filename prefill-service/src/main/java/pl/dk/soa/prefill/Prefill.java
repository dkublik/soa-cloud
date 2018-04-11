package pl.dk.soa.prefill;

public class Prefill {

    private String firstName;
    private String lastName;
    private String email;

    public Prefill(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
}
