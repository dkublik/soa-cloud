package pl.dk.soa.apply;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 *  must be up & running
 *  ats-apply-service/src/main/java/pl.dk.soa.apply.ApplyApplication http://localhost:8080
 *  prefill-service/src/main/java/pl.dk.soa.prefill.PrefillApplication http://localhost:8081
 */
public class ApplyTest {

    @Test
    public void shouldSuccessfullyApply() throws Exception {
        // given
        userIsCreatedInPrefill();
        RequestSpecification request = given()
                .contentType("application/vnd.apply.v2+json")   // TU
                .body(new JSONObject()
                        .put("candidateId", "mcurie")
                        .put("messageToRecruiter", "rady to work")
                        .put("listingId", "12445")
                        .toString()
                );

        // when
        Response response = request.when().post("http://localhost:8080/job-applications");

        // then
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .contentType(ContentType.JSON)
                .body("applicationId", notNullValue())
                .body("priority", is("HIGH"))
                .body("status", is("ACCEPTED")); // TU
    }

    private void userIsCreatedInPrefill() throws JSONException {
        Person person = Fairy.create(Locale.forLanguageTag("pl")).person(PersonProperties.withUsername("mcurie"));
        RequestSpecification request = given()
                .contentType(JSON)
                .body(new JSONObject()
                        .put("dateOfBirth", person.getDateOfBirth())
                        .put("email", person.getEmail())
                        .put("firstName", person.getFirstName())
                        .put("lastName", person.getLastName())
                        .put("nationalIdentificationNumber", person.getNationalIdentificationNumber())
                        .put("passportNumber", person.getPassportNumber())
                        .put("phone", person.getTelephoneNumber())
                        .put("address", new JSONObject()
                                .put("city", person.getAddress().getCity())
                                .put("country", "Poland")
                                .put("street", person.getAddress().getStreet())
                                .put("zip", person.getAddress().getPostalCode())
                        )
                        .put("yearOfExperience", 20)
                        .toString()
                );
        request.when().put("http://localhost:8081/v1/prefill/for-candidate/mcurie");
    }

}
