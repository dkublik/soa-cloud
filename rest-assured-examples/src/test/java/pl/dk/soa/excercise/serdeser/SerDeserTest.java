package pl.dk.soa.excercise.serdeser;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 *  must be up & running
 *  ats-apply-service/src/main/java/pl.dk.soa.apply.ApplyApplication http://localhost:8080
 *  prefill-service/src/main/java/pl.dk.soa.prefill.PrefillApplication http://localhost:8081
 */
public class SerDeserTest {

    /*
        zadanie:
        pozbadz sie JSONObject() zamieniajac na prosty obiekt javowy (POJO)
        zdeserualizuj odpowiedz jak w DeserializationExample i sprawdz wartosci
        applicationId, priority na zdeserializowanym obiekcie

     */

    @Test
    public void shouldGetDataForPrefill() {
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
                .statusCode(202)
                .contentType(ContentType.JSON)
                .body("applicationId", notNullValue())
                .body("priority", is("LOW"));
    }

}
