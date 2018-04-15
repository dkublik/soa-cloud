package pl.dk.soa.excercise.jsonpath;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

/**
 *  candidate-profile-service/src/main/java/pl.dk.soa.candidate/CandidateApplication must be up & running
 *  http://localhost:8082
 */
public class JsonPathTest {

    static final String CANDIDATE_HOST = "http://localhost:8082";

    @Test
    public void loginOf2ndCandidateFromTheEndIs() {
        // when
        Response response = when().get(CANDIDATE_HOST + "/v0/candidates/profile");

        // then
        // kandydat drugi od konca
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("TODO", is("mpatton"));
    }

    @Test
    public void biggestRequestedSalary() {
        // when
        Response response = when().get(CANDIDATE_HOST + "/v0/candidates/profile");

        // then
        // najwyzsze zadane wyngarodzenie
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("TODO", is(21000.0f));
    }

    @Test
    public void allRequestedSalariesSum() {
        // when
        Response response = when().get(CANDIDATE_HOST + "/v0/candidates/profile");

        // then
        // suma wszystkich zadanych wynagrodzen
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("TODO", is(28400.0));
    }

    @Test
    public void numberOfAvailableDaysForBritney() {
        // when
        Response response = when().get(CANDIDATE_HOST + "/v0/candidates/profile");

        // then
        // przez ile dni uzytkownik britney jest dostepna
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("TODO", is(6));
    }

    @Test
    public void everyOneWithWpPlLogin() {
        // when
        Response response = when().get(CANDIDATE_HOST + "/v0/candidates/profile");

        // then
        // wszyscy ktorzy maja email w wp.pl
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("TODO", hasItems("mpatton", "mrpresident"));
    }

    @Test
    public void availableAtMoreThan3Days() {
        // when
        Response response = when().get(CANDIDATE_HOST + "/v0/candidates/profile");

        // then
        // tes osoby sa dostepne przez wiecej niz 3 dni
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("TODO", hasItems("just_britney", "mhamill", "mpatton"));
    }

    @Test
    public void everyLoginUpperCased() {
        // when
        Response response = when().get(CANDIDATE_HOST + "/v0/candidates/profile");

        // then
        // wszystkie loginy, ale w upper case
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("TODO",
                        hasItems("BECKY007", "JUST_BRITNEY", "MHAMILL", "MPATTON", "MRPRESIDENT"));
    }



    @Test
    public void ifPhotoExistsThenItShouldEndWithGifOrJpgOrPng() {
        // when
        Response response = when().get(CANDIDATE_HOST + "/v0/candidates/profile");

        // then
        // wszystkie rozszerzenia fotografii
        // uwaga: fotografie moga nie istniec!
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("TODO",
                    everyItem(anyOf(is("png"), is("jpg"), is("gif"))));
    }


}
