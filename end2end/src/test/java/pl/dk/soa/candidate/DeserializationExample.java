package pl.dk.soa.candidate;

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
import static pl.dk.soa.Hosts.CANDIDATE_HOST;

public class DeserializationExample {

    @Test
    public void shouldGetProfileDetails() {
        // given
        // to ignore address filed that we don't care about
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false))
        );
        RequestSpecification requestSpecification = given()
                .pathParam("candidateId", "mHamill");

        // when
        Response response = requestSpecification.when().get(CANDIDATE_HOST + "/v0/candidates/profile/{candidateId}");

        // then
        response.then()
                .statusCode(200);
        CandidatePersonalDetails candidatePersonalDetails = response.as(CandidatePersonalDetails.class);
        Assert.assertThat(candidatePersonalDetails.getLogin(), is("mhamill"));
    }

}