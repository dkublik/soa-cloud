package pl.dk.soa.prefill.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import pl.dk.soa.prefill.Prefill;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PrefillService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PrefillService.class);

    private Map<String, Prefill> prefillData = new HashMap<>();

    public Optional<Prefill> getPrefillData(String candidateId) {
        log.info("getting preffill data for {}", candidateId);
        return Optional.ofNullable(prefillData.get(candidateId.toLowerCase()));
    }

    public void add(String candidateId, Prefill prefill) {
        prefillData.put(candidateId, prefill);
    }

}
