package pl.dk.soa.candidate.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static java.util.Arrays.asList;

@Service
class CandidatesDemoData {

    private final CandidateService candidateService;

    CandidatesDemoData(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostConstruct
    void postConstruct() {
        candidateService.addCandidate(
                new CandidatePersonalDetails("mhamill", "Mark", "Hamill", "mark.hamill@gmail.com", "+48 111 111 111",
                        "d:\\ulubione\\mhamill.jpg", "jedi master", 3, 1200, asList(9, 12, 30, 11, 8, 4),
                        new CandidateAddress("Poland", "Warsaw", "Zaruby 10", "11-220")));
        candidateService.addCandidate(
                new CandidatePersonalDetails("just_britney", "Britney", "Spears", "just_britney@spears.pl", "+48 111 111 112",
                        "d:\\ulubione\\britney.gif", "singer and dancer", 1, 2500,  asList(1, 2, 4, 5, 6, 8),
                        new CandidateAddress("Poland", "Bydgoszcz", "Czerwonego Kapturka 22/13", "82-110")));
        candidateService.addCandidate(
                new CandidatePersonalDetails("becky007", "Kent", "Beck", "extreme@yahoo.com", "+48 221 232 234",
                        "", "tdd for the win", 2, 3200, asList(),
                        new CandidateAddress("Lithuania", "Vilnius", "Dauksos 5", "02102")));
        candidateService.addCandidate(
                new CandidatePersonalDetails("mpatton", "Mike", "Patton", "haveSomeFaith@wp.pl", "+48 221 332 332",
                        "e:\\mike\\photos\\me.gif", "Those haunting rhymes are keeping the time", 6, 500, asList(9, 12, 30, 11, 8, 4),
                        new CandidateAddress("Latvia", "Riga", "Palasta Iela 9", "LV-1050")));
        candidateService.addCandidate(
                new CandidatePersonalDetails("mrpresident", "Barack", "Obama", "mrPresident@wp.pl", "+48 111 111 113",
                        "", "retired", 21, 21000, asList(),
                        new CandidateAddress("Poland", "Warsaw", "Plac Bankowy 12", "11-023")));
    }

}
