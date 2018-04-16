package pl.dk.soa.apply;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpStatus;

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
                .contentType(JSON)
                .body(new JSONObject()
                        .put("candidateId", "mcurie")
                        .put("messageToRecruiter", "rady to work")
                        .put("listingId", "12445")
                        .toString()
                );

        // when
        Response response = request.when().post("http://localhost:8080/v1/job-applications");

        // then
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .contentType(ContentType.JSON)
                .body("applicationId", notNullValue())
                .body("status", is("ACCEPTED"))
                .body("priority", is("HIGH"));
    }

    private void userIsCreatedInPrefill() throws JSONException {
        RequestSpecification request = given()
                .contentType(JSON)
                .body(new JSONObject()
                        .put("dateOfBirth", "02071987")
                        .put("email", "myEmail@candidate.com")
                        .put("firstName", "Maria")
                        .put("lastName", "Curie")
                        .put("nationalIdentificationNumber", "84030115671")
                        .put("passportNumber", "82020713898")
                        .put("phone", "22674455")
                        .put("address", new JSONObject()
                                .put("city", "Szczecin")
                                .put("country", "Polska")
                                .put("street", "Kolorowa 12/32")
                                .put("zip", "12-223")
                        )
                        .put("yearOfExperience", 20)
                        .toString()
                );
        request.when().put("http://localhost:8081/v1/prefill/for-candidate/mcurie");
    }

}