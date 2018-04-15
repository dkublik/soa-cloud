package pl.dk.soa.apply;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.awaitility.Awaitility;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dk.soa.BasicDiscoveryClient;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 *  must be up & running
 *  ats-apply-service/src/main/java/pl.dk.soa.apply.ApplyApplication http://localhost:8080
 *  prefill-service/src/main/java/pl.dk.soa.prefill.PrefillApplication http://localhost:8081
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplyTest {

    @Autowired
    BasicDiscoveryClient discoveryClient;

    @Test
    public void shouldSuccessfullyApply() throws Exception {
        // given
        userIsCreatedInPrefill();

        // when
        Response response = applyRequestForMCurie().when().post(discoveryClient.getHost("ATS-APPLY-SERVICE") + "/job-applications");

        // then
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .contentType(ContentType.JSON)
                .body("applicationId", notNullValue())
                .body("priority", is("HIGH"));
        assertApplicationStatus(response);
    }

    private void assertApplicationStatus(Response response) {
        String applicationId = response.jsonPath().getString("applicationId");
        Awaitility.await().atMost(2, TimeUnit.SECONDS).until(() -> isAccepted(applicationId));
    }

    private boolean isAccepted(String applicationId) {
        return given()
                .contentType("application/vnd.apply.v4+json")
                .when()
                .get("http://localhost:8080/job-applications/" + applicationId)
                .jsonPath()
                .getString("status")
                .equals("ACCEPTED");
    }

    private RequestSpecification applyRequestForMCurie() throws JSONException {
        return given()
                .contentType("application/vnd.apply.v4+json")
                .body(new JSONObject()
                        .put("candidateId", "mcurie")
                        .put("messageToRecruiter", "rady to work")
                        .put("listingId", "12445")
                        .toString()
                );
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
