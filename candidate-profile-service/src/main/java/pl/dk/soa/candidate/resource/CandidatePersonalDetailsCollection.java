package pl.dk.soa.candidate.resource;

import pl.dk.soa.candidate.service.CandidatePersonalDetails;

import java.util.List;

public class CandidatePersonalDetailsCollection {

    private List<CandidatePersonalDetails> candidates;

    public CandidatePersonalDetailsCollection(List<CandidatePersonalDetails> candidates) {
        this.candidates = candidates;
    }

    public CandidatePersonalDetailsCollection() {
    }

    public List<CandidatePersonalDetails> getCandidates() {
        return this.candidates;
    }

    public void setCandidates(List<CandidatePersonalDetails> candidates) {
        this.candidates = candidates;
    }
}
