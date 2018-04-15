package pl.dk.soa.example.awaitility;

import org.awaitility.Awaitility;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.when;

/**
 *  must be up & running
 *  ats-apply-service/src/main/java/pl.dk.soa.apply.ApplyApplication http://localhost:8080
 */
public class AwaitilityExample {

    @Test
    public void second64ShouldBePresent() {
        Awaitility.await().atMost(60, TimeUnit.SECONDS).until(() -> is54Seconds());
    }

    private boolean is54Seconds() {
        return when().get("http://localhost:8080/time")
                .jsonPath().getInt("second") == 54;
    }

}
