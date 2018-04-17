package pl.dk.soa.company;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.awaitility.Awaitility;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dk.soa.BasicDiscoveryClient;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 *  must be up & running
 *  listings-service/src/main/java/pl.dk.soa.listing.ListingsApplication http://localhost:8090
 *  company-service/src/main/java/pl.dk.soa.company.CompanyApplication http://localhost:8091
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CompanyTest {

    // zadanie:
    // przetestuj, ze dla firmy, ktora ma status memberStatus = PLATINIUM
    // utworzony listing, bedzie mial  priority = "HIGH"


    // endpointy - do sprawdzenia ze swaggera
    // wersje api - do sprawdzenia ze swaggera

    @Autowired
    BasicDiscoveryClient discoveryClient;

    @Test
    public void shouldSuccessfullyCreateListing() throws Exception {
        // given
        companyIsCreated();

        // when
        Response response = createListingForDkCompany().when().post(discoveryClient.getHost("listings-service") + "/listings");

        // then
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .contentType(ContentType.JSON)
                .body("listingId", notNullValue())
                .body("priority", is("HIGH"));
        assertListingStatus(response);
    }

    private void assertListingStatus(Response response) {
        String listingId = response.jsonPath().getString("listingId");
        Awaitility.await().atMost(2, TimeUnit.SECONDS).until(() -> isAccepted(listingId));
    }

    private boolean isAccepted(String listingId) {
        return given()
                .contentType("application/vnd.listings.v3+json")
                .when()
                .get(discoveryClient.getHost("listings-service") + "/listings/" + listingId)
                .jsonPath()
                .getString("status")
                .equals("ACCEPTED");
    }

    private RequestSpecification createListingForDkCompany() throws JSONException {
        return given()
                .contentType("application/vnd.listings.v3+json")
                .body(new JSONObject()
                        .put("company", "dkCompany")
                        .put("jobDescription", "extra job")
                        .toString()
                );
    }

    private void companyIsCreated() throws JSONException {
        Company company = Fairy.create(Locale.forLanguageTag("pl")).company();
        RequestSpecification request = given()
                .contentType("application/vnd.company.v3+json")
                .body(new JSONObject()
                        .put("name", company.getName())
                        .put("domain", company.getDomain())
                        .put("email", company.getEmail())
                        .put("vatIdentificationNumber", company.getVatIdentificationNumber())
                        .put("memberStatus", "PLATINIUM")
                        .toString()
                );
        request.when().put("http://localhost:8091/company/dkCompany");
    }

}
