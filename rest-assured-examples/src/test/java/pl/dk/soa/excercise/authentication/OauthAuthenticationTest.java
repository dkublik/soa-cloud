package pl.dk.soa.excercise.authentication;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 *  candidate-profile-service/src/main/java/pl.dk.soa.candidate/CandidateApplication must be up & running
 *  http://localhost:8082
 */
public class OauthAuthenticationTest {

    @Test
    public void shouldGetDataThatRequireOauth2Authentication() {
        // wzorujac sie na BasicAuthenticationExample
        // napisz test korzystajacy z autentykacji oauth2
        //
        // zabezpieczony przez oauth2 endpoint to "http://localhost:8082/voauth/candidates/profile"
        //
        // nie musisz pobierac tokenu z zewnetrznego serwisu, przyjmij, ze jest on rowny
        // token: "1111-1111-1111"
        // given
        RequestSpecification requestSpecification = given().auth().oauth2("1111-1111-1111");

        // when
        Response response = requestSpecification.when().get("http://localhost:8082/voauth/candidates/profile");

        // then
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("name", is("Mark"))
                .body("surname", is("Hamill"))
                .body("email", is("mark.hamill@gmail.com"));
    }

}