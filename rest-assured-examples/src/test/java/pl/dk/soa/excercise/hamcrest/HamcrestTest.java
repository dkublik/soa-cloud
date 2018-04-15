package pl.dk.soa.excercise.hamcrest;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.number.IsCloseTo;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.hamcrest.text.IsEqualIgnoringWhiteSpace;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

/**
 *  candidate-profile-service/src/main/java/pl.dk.soa.candidate/CandidateApplication must be up & running
 *  http://localhost:8082
 */
public class HamcrestTest {

    @Test
    public void descriptionShouldFitIgnoringCase() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        // opis porownany do "those haunting rhymes are keeping the time" - powinien przechodzic
        // potrzebny matcher ingorujacy wielkosc liter
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
                // TODO body
    }

    @Test
    public void descriptionShouldFitIgnoringWhiteSpaces() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        // opis porownany do "Those     haunting     rhymes are keeping the time" - powinien przechodzic
        // potrzebny matcher ingorujacy biale znaki
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
                // TODO body
    }

    @Test
    public void shouldBeInPhotosWithGifExtension() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        // sciezka ze zdjeciem powinna zawierac zarowno katalog photos jak i rozszerzenie .gif
        // potrzebny matcher ingorujacy biale znaki
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
                // TODO body
    }

    @Test
    public void salaryMoreThan100orLessOrEqualThen500() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        // ostatnio zadane wynagrodzenie powinno byc albo wieksze niz 1000 mniejsze / rowne 500
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
                // TODO body
    }

    @Test
    public void salaryShouldBeClose550GivenOrTake100() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        // ostatnio zadane wynagrodzenie powinno byc rowne 550 z dokladnoscia do 100
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
                // TODO body
    }

    @Test
    public void shouldBeAvailableAtDays8and9and11and12() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
                // TODO body
    }

    @Test
    public void shouldBeDescribedAsToFussyWhenRequestedSalaryMoreThan1000() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        // jesli kandydat zarzyczyl sobie wiecej niz 1000 to powinien wystapic blad z opisem,
        // ze jest wybredny
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
                // TODO body
    }

/*    @Test
    public void customMatcher() {
        // when
        Response response = when().get("http://localhost:8082/v0/candidates/profile/mpatton");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("address", AddressMatcher.hasAddress("Latvia", "Riga", "Palasta Iela 9"));
    }*/

}