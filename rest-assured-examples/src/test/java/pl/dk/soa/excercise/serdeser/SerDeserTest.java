package pl.dk.soa.excercise.serdeser;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.json.JSONObject;
import org.junit.Assert;
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
                .body(new Application("just_britney",
                        "please hire me", "123"));

        // when
        Response response = request.when().post("http://localhost:8080/v1/job-applications");

        // then
        AppIdResponse appIdResponse = JsonPath.from(response.asString()).getObject("$", AppIdResponse.class);
        Assert.assertThat(appIdResponse.getApplicationId(), CoreMatchers.notNullValue());
        Assert.assertThat(appIdResponse.getPriority(), CoreMatchers.is("LOW"));
    }

}
