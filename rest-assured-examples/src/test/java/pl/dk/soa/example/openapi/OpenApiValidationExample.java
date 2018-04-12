package pl.dk.soa.example.openapi;

import com.atlassian.oai.validator.restassured.SwaggerValidationFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 *  candidate-profile-service/src/main/java/pl.dk.soa.candidate/CandidateApplication must be up & running
 *  http://localhost:8082
 */
public class OpenApiValidationExample {

    SwaggerValidationFilter validationFilter = new SwaggerValidationFilter("candidate-swagger-v1.yaml");

    @Test
    public void shouldValidateAgainstOpenApiSpecification() {
        // given:
        RequestSpecification request = given()
                .filter(validationFilter);

        // when:
        Response response = request.when()
                .get("http://localhost:8082/v1/candidates/profile");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("login", is("mhamill"));
    }

}