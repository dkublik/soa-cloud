package pl.dk.soa.prefill;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.dk.soa.End2EndApplication;
import pl.dk.soa.Hosts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@ContextConfiguration(classes = End2EndApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class PrefillTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void shouldGetDataForPrefill() {
        // given
        String prefillHost = discoveryClient.getInstances("prefill-service").get(0).getUri().toString();
        RequestSpecification requestSpecification = given().pathParam("candidateId", "mHamill");

        // when
        Response response = requestSpecification.when().get(Hosts.PREFILL_HOST + "/v1/prefill/for-candidate/{candidateId}");

        // then
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName", is("Mark"))
                .body("lastName", is("Hamill"))
                .body("email", is("mark.hamill@gmail.com"));
    }

}