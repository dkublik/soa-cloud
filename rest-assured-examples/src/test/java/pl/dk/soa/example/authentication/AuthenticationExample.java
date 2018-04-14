package pl.dk.soa.example.authentication;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 *  candidate-profile-service/src/main/java/pl.dk.soa.candidate/CandidateApplication must be up & running
 *  http://localhost:8082
 */
public class AuthenticationExample {

    @Test
    public void shouldGetDataThatRequireAuthentication() {
        // given
        RequestSpecification requestSpecification = given().auth().basic("mhamill", "pass");

        // when
        Response response = requestSpecification.when().get("http://localhost:8082/vb/candidates/profile");

        // then
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("name", is("Mark"))
                .body("surname", is("Hamill"))
                .body("email", is("mark.hamill@gmail.com"));
    }

}