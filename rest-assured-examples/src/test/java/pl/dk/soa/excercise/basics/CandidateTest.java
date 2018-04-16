package pl.dk.soa.excercise.basics;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

/**
 *  candidate-service/src/main/java/pl.dk.soa.candidate.CandidateApplication must be up & running
 *  http://localhost:8082
 */
public class CandidateTest {

    @Test
    public void shouldGetProfileDetails() {
        // given
        RequestSpecification requestSpecification = RestAssured.given()
                .pathParam("candidateId", "mHamill");

        // when
        Response response = requestSpecification.when().get( "http://localhost:8082/v0/candidates/profile/{candidateId}");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("login", CoreMatchers.is("mhamill"))
                .body("name", CoreMatchers.is("Mark"))
                .body("surname", CoreMatchers.is("Hamill"))
                .body("email", CoreMatchers.is("mark.hamill@gmail.com"))
                .body("address.country", CoreMatchers.is("Poland"))
                .body("address.city", CoreMatchers.is("Warsaw"))
                .body("address.street", CoreMatchers.is("Zaruby 10"));

    }

}