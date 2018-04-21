package pl.dk.soa.excercise.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.Map;

public class AddressMatcher extends BaseMatcher {

    private final String country;
    private final String city;
    private final String street;

    public static AddressMatcher hasAddress(String country, String city, String street) {
        return new AddressMatcher(country, city, street);
    }

    AddressMatcher(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    @Override
    public boolean matches(Object o) {
        Map<String, String> address = (Map<String, String>) o;
        return address.get("country").equals(country) &&
                address.get("city").equals(city) &&
                address.get("street").equals(street);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("should be ")
                .appendValue(country)
                .appendText(" ")
                .appendValue(city)
                .appendText(" ")
                .appendValue(street)
                .appendText(" ");
    }


}
