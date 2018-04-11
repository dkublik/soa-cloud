package pl.dk.soa.candidate.service;

import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Comparator.comparing;

@Service
public class CandidateService {

    private Map<String, CandidatePersonalDetails> candidates = new HashMap<>();

    public void addCandidate(CandidatePersonalDetails candidate) {
        candidates.put(candidate.getLogin().toLowerCase(), candidate);
    }

    public Optional<CandidatePersonalDetails> getCandidate(String login) {
        return Optional.ofNullable(candidates.get(login.toLowerCase()));
    }

    public List<CandidatePersonalDetails> getCandidates() {
        List<CandidatePersonalDetails> allCandidates = new ArrayList<>(candidates.values());
        allCandidates.sort(comparing(CandidatePersonalDetails::getLogin));
        return allCandidates;
    }



}
