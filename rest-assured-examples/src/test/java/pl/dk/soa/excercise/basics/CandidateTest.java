package pl.dk.soa.excercise.basics;

import org.junit.Test;

/**
 *  candidate-service/src/main/java/pl.dk.soa.candidate.CandidateApplication must be up & running
 *  http://localhost:8082
 */
public class CandidateTest {

    @Test
    public void shouldGetProfileDetails() {
        // given / when
        // test zasobu: GET http://localhost:8082/v0/candidates/profile/{candidateId}
        // testowany uzytkownik mHamill

        // then
        // do sprawdzenia co najmniej:
        // status odpowiedzi
        // content type odpowiedzi
        // login
        // name
        // surname
        // email
        // address.country
        // address.city
        // address.street


        // hint:
        // odpowiedz mozna podejrzec pod http://localhost:8082/swagger-ui.html
    }

}