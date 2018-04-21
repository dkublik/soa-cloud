package pl.dk.soa.excercise.serdeser;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 *  must be up & running
 *  candidate-service/src/main/java/pl.dk.soa.candidate.CandidateApplication http://localhost:8082
 */
public class DeserTest {

    /*
        na podstawie DeserializationExample
        napisac test uderzajacy do http://localhost:8082/v0/candidates/profile/{candidateId}
        deserializujacy tylko adres z odpowiedzi
        i assertujacy na adresie country i city
     */
    @Test
    public void shouldGetProfileDetails() {
        // given
        RequestSpecification requestSpecification = given()
                .pathParam("candidateId", "mHamill");

        // when
        Response response = requestSpecification.when().get("http://localhost:8082/v0/candidates/profile/{candidateId}");

        // then
        response.then()
                .statusCode(200);
        CandidateAddress candidateAddress = JsonPath.from(response.asString()).getObject("address", CandidateAddress.class);
        Assert.assertThat(candidateAddress.getCity(), CoreMatchers.is("Warsaw"));

    }

}
