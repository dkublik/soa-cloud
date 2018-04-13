package pl.dk.soa.example.jsonpath;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

/**
 *  candidate-profile-service/src/main/java/pl.dk.soa.candidate/CandidateApplication must be up & running
 *  http://localhost:8082
 */
public class JsonPathExamples {

    @Test
    public void propertiesByDot() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("candidates[1].login", is("just_britney"))
                .body("candidates[1].address.country", is("Poland"));
    }

    @Test
    public void propertiesByDotReverse() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("candidates[-1].login", is("mrpresident"))
                .body("candidates[-1].address.country", is("Poland"))
                .body("candidates[-1].address.country[1..5]", is("oland"));
    }

    @Test
    public void find() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("candidates.find { it.login == 'just_britney' }.address.city", is("Bydgoszcz"))
                .body("candidates.findAll { it.address.city == 'Warsaw' }.login", hasItems("mhamill", "mrpresident"))
                .body("candidates.findAll { it.email.endsWith('pl') }.login", hasItems("just_britney", "mrpresident"));
    }

    @Test
    public void min() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("candidates.min { it.address.zip }.login", is("becky007"));
    }

    @Test
    public void aggregates() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("candidates.size()", is(5))
                .body("candidates.login.collect { it.length() }.sum()", is(45));
    }

    @Test
    public void extractingJsonPathObject() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile");

        // then
        int size = JsonPath.from(response.asString()).getInt("candidates.size()");
        Assert.assertEquals(5, size);
    }

}