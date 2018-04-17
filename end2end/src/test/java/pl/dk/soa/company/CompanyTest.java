package pl.dk.soa.company;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void shouldSuccessfullyCreateListing() {
    }

}
