package pl.dk.soa.example.basics;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 *  prefill-service/src/main/java/pl.dk.soa.prefill.PrefillApplication must be up & running
 *  http://localhost:8081
 */
public class PrefillTest {

    @Test
    public void shouldGetDataForPrefill() {
        // given
        RequestSpecification requestSpecification = given()
                .pathParam("candidateId", "mHamill");

        // when
        Response response = requestSpecification.when()
                .get("http://localhost:8081/v1/prefill/for-candidate/{candidateId}");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName", is("Mark"))
                .body("lastName", is("Hamill"))
                .body("email", is("mark.hamill@gmail.com"));
    }

}