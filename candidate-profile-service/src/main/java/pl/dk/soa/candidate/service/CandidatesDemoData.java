package pl.dk.soa.candidate.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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
                        "d:\\ulubione\\mhamill.jpg", "jedi master", new CandidateAddress("Poland", "Warsaw", "Zaruby 10", "11-220")));
        candidateService.addCandidate(
                new CandidatePersonalDetails("just_britney", "Britney", "Spears", "just_britney@spears.pl", "+48 111 111 112",
                        "d:\\ulubione\\britney.gif", "singer and dancer", new CandidateAddress("Poland", "Bydgoszcz", "Czerwonego Kapturka 22/13", "82-110")));
        candidateService.addCandidate(
                new CandidatePersonalDetails("mrpresident", "Barack", "Obama", "mrPresident@wp.pl", "+48 111 111 113",
                        "", "retired", new CandidateAddress("Poland", "Warsaw", "Plac Bankowy 12", "11-023")));
    }

}
