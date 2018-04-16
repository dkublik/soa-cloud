package pl.dk.soa.apply;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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
        RequestSpecification request = given()
                .contentType(JSON)
                .body(new JSONObject()
                        .put("candidateId", "just_britney")
                        .put("messageToRecruiter", "please hire me")
                        .put("listingId", "123")
                        .toString()
                );

        // when
        Response response = request.when().post("http://localhost:8080/v1/job-applications");

        // then
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .contentType(ContentType.JSON)
                .body("applicationId", notNullValue())
                .body("priority", is("LOW"))
                .body("status", is("ACCEPTED"));
    }

    // obecnie aplikacja jest tworzona z priority = "LOW",
    // priority liczony jest ze wzgledu na staz aplikujacego
    // yearOfExperience < 5 lat => LOW
    // <=5 yearOfExperience < 10 => MEDIUM
    // yearOfExperience >= 10 => HIGH
    //
    // przepisz test tak aby sprawdzal, ze dla uzytkownika, ze stazem powyzej 10 lat
    // aplikacja bedzie zwracala priority: HIGH
    // zauwaz, ze zaden z obecnych uzytkownikow
    // http://localhost:8081/v1/prefill/for-candidate/just_britney
    // http://localhost:8081/v1/prefill/for-candidate/mhamill
    // http://localhost:8081/v1/prefill/for-candidate/mrpresident
    // nie ma stazu powyzej 10 lat,
    // aby przeprowadzic test musisz zasilic prefill-service uzytkownikiem ze stazem > 10 lat
    // endpoint do zasilania znajdziesz przez swaggera

}