package pl.dk.soa.example.stubbed;

import com.atlassian.oai.validator.restassured.SwaggerValidationFilter;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 *  must be up & running
 *  ats-apply-service/src/main/java/pl.dk.soa.apply.ApplyApplication http://localhost:8080
 */
public class ApplyTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(8081));


    @Test
    public void shouldSuccessfullyApply() throws Exception {
        // given
        RequestSpecification request = given()
                .contentType(JSON)
                .body(new JSONObject()
                        .put("candidateId", "just_britney")
                        .put("messageToRecruiter", "please hire me")
                        .put("listingId", "123")
                        .toString()
                );

        // when
        Response response = request.when().post("http://localhost:8080/v1/job-applications");

        // then
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .contentType(ContentType.JSON)
                .body("applicationId", notNullValue())
                .body("priority", is("HIGH"));
    }


    SwaggerValidationFilter validationFilter = new SwaggerValidationFilter("prefill-swagger.json");

    @Test
    public void shouldValidateAgainstOpenApiSpecification() {
        // given:
        RequestSpecification request = given()
                .filter(validationFilter);

        // when:
        Response response = request.when()
                .get("http://localhost:8081/v1/prefill/for-candidate/just_britney");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

}