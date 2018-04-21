package pl.dk.soa.example.hamcrest;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

/**
 *  candidate-profile-service/src/main/java/pl.dk.soa.candidate/CandidateApplication must be up & running
 *  http://localhost:8082
 */
public class HamcrestExamples {

    @Test
    public void shouldConvertApplication_Gen1() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");


        // then
        CandidatePersonalDetails candidate = response.as(CandidatePersonalDetails.class);
        Assert.assertTrue(candidate.getLogin().equals("mpatton"));
        Assert.assertTrue(candidate.getEmail() != null);
        Assert.assertTrue(candidate.getPersonalDescription().equals("Those haunting rhymes are keeping the time"));
    }

    @Test
    public void shouldConvertApplication_Gen2() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        CandidatePersonalDetails candidate = response.as(CandidatePersonalDetails.class);
        Assert.assertEquals("mpatton", candidate.getLogin());
        Assert.assertNotNull(candidate.getEmail());
        Assert.assertEquals("Those haunting rhymes are keeping the time", candidate.getPersonalDescription());
    }

    @Test
    public void shouldConvertApplication_Gen3() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        CandidatePersonalDetails candidate = response.as(CandidatePersonalDetails.class);
        Assert.assertThat(candidate.getEmail(), CoreMatchers.not(CoreMatchers.nullValue()));
        Assert.assertThat(candidate.getLogin(), CoreMatchers.is("mpatton"));
        Assert.assertThat(candidate.getPersonalDescription(), CoreMatchers.is("Those haunting rhymes are keeping the time"));
    }

    @Test
    public void shouldConvertApplication_Gen3_restAssured() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("email", CoreMatchers.not(CoreMatchers.nullValue()))
                .body("login", CoreMatchers.is("mpatton"))
                .body("personalDescription", CoreMatchers.is("Those haunting rhymes are keeping the time"));
    }

    @Test
    public void shouldCompareToBigDecimal() {
        // given
        RequestSpecification request = given().
                config(RestAssured.config().jsonConfig(JsonConfig.jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)));

        // when
        Response response = request.when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("lastSalaryRequested", CoreMatchers.is(BigDecimal.valueOf(500.0)));
    }

    @Test
    public void stringUtils() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("login", IsEqualIgnoringCase.equalToIgnoringCase("mPATTon"))
                .body("personalDescription", Matchers.stringContainsInOrder(Arrays.asList("haunting", "rhymes", "time")));
    }

    @Test
    public void beans() {
        // given
        CandidateAddress referenceAddress = new CandidateAddress("Latvia", "Riga", "Palasta Iela 9", "LV-1050");

        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        CandidatePersonalDetails candidate = response.as(CandidatePersonalDetails.class);
        Assert.assertThat(candidate.getAddress(), Matchers.hasProperty("country", CoreMatchers.is("Latvia")));
        Assert.assertThat(candidate.getAddress(), Matchers.samePropertyValuesAs(referenceAddress));
    }

    @Test
    public void collections() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("avaialbleAtDays", IsCollectionWithSize.hasSize(6))
                .body("avaialbleAtDays", CoreMatchers.everyItem(Matchers.greaterThan(3)))
                .body("avaialbleAtDays", CoreMatchers.everyItem(CoreMatchers.anyOf(Matchers.greaterThan(20), Matchers.lessThan(13))))
                .body("avaialbleAtDays", CoreMatchers.hasItem(Matchers.greaterThan(8)));
    }

    @Test
    public void descriptions() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("login",
                        CoreMatchers.describedAs("sorry, only %0 allowed", Matchers.is("britney"), "britney"));
    }


    @Test
    public void customMatcher() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", HasPhotoMatcher.hasPhoto());
    }

}