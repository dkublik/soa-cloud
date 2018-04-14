package pl.dk.soa.example.jfairy;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import io.codearte.jfairy.producer.text.TextProducer;
import org.junit.Test;

import java.util.Locale;

public class JFairyExample {

    @Test
    public void userBasicParams() {
        Person person = Fairy.create().person();

        System.out.println("--- " + person.getUsername());
        System.out.println("--- " + person.getFirstName());
        System.out.println("--- " + person.getLastName());
        System.out.println("--- " + person.getEmail());
        System.out.println("--- " + person.getCompanyEmail());
        System.out.println("--- " + person.getPassportNumber());
        System.out.println("--- " + person.getDateOfBirth());
        System.out.println("--- " + person.getNationalIdentificationNumber());
        System.out.println("--- " + person.getTelephoneNumber());
        System.out.println("--- " + person.getAddress());
    }

    @Test
    public void userLocalizedBasicParams() {
        Person person = Fairy.create(Locale.forLanguageTag("pl")).person();

        System.out.println("--- " + person.getUsername());
        System.out.println("--- " + person.getFirstName());
        System.out.println("--- " + person.getLastName());
        System.out.println("--- " + person.getEmail());
        System.out.println("--- " + person.getCompanyEmail());
        System.out.println("--- " + person.getPassportNumber());
        System.out.println("--- " + person.getDateOfBirth());
        System.out.println("--- " + person.getNationalIdentificationNumber());
        System.out.println("--- " + person.getTelephoneNumber());
        System.out.println("--- " + person.getAddress());
    }

    @Test
    public void specificUserLocalizedBasicParams() {
        Person person = Fairy
                .create(Locale.forLanguageTag("pl")).person(
                        PersonProperties.female(), PersonProperties.ageBetween(18, 36),
                        PersonProperties.withUsername("poziomka32"));

        System.out.println("--- " + person.getUsername());
        System.out.println("--- " + person.getFirstName());
        System.out.println("--- " + person.getLastName());
        System.out.println("--- " + person.getEmail());
        System.out.println("--- " + person.getCompanyEmail());
        System.out.println("--- " + person.getPassportNumber());
        System.out.println("--- " + person.getDateOfBirth());
        System.out.println("--- " + person.getNationalIdentificationNumber());
        System.out.println("--- " + person.getTelephoneNumber());
        System.out.println("--- " + person.getAddress());
    }

    @Test
    public void descriptions() {
        TextProducer text = Fairy.create().textProducer();

        System.out.println("--- " + text.loremIpsum());
        System.out.println("--- " + text.text());
        System.out.println("--- " + text.sentence());
        System.out.println("--- " + text.word(4));
        System.out.println("--- " + text.randomString(20));
    }

    @Test
    public void localizedDescriptions() {
        TextProducer text = Fairy.create(Locale.forLanguageTag("pl")).textProducer();

        System.out.println("--- " + text.text());
        System.out.println("--- " + text.sentence());
        System.out.println("--- " + text.word(4));
        System.out.println("--- " + text.randomString(20));
    }

}
