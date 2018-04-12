package pl.dk.soa.example.deserialization;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 *  candidate-profile-service/src/main/java/pl.dk.soa.candidate/CandidateApplication must be up & running
 *  http://localhost:8082
 */
public class DeserializationExample {

    @Test
    public void shouldGetProfileDetails() {
        // given
        // to ignore address field that we don't care about
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false))
        );
        RequestSpecification requestSpecification = given()
                .pathParam("candidateId", "mHamill");

        // when
        Response response = requestSpecification.when().get("http://localhost:8082/v0/candidates/profile/{candidateId}");

        // then
        response.then()
                .statusCode(200);
        CandidatePersonalDetails candidatePersonalDetails = response.as(CandidatePersonalDetails.class);
        Assert.assertThat(candidatePersonalDetails.getLogin(), is("mhamill"));
    }

}