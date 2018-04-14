package pl.dk.soa.apply.resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import pl.dk.soa.apply.store.ApplyRepository;
import pl.dk.soa.apply.store.StoredApplication;

import java.util.List;

@Service
class ApplyService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ApplyService.class);

    private final ApplyRepository applyRepository;

    private final ApplicationConverter applicationConverter;

    ApplyService(ApplyRepository applyRepository, ApplicationConverter applicationConverter) {
        this.applyRepository = applyRepository;
        this.applicationConverter = applicationConverter;
    }

    public StoredApplication apply(Application application) {
        log.info("application for listing id {} received for {}", application.getListingId(), application.getCandidateId());
        StoredApplication storedApplication = applicationConverter.toStoredApplication(application);
        applyRepository.store(storedApplication);
        return storedApplication;
    }

    public List<StoredApplication> findAll() {
        return applyRepository.findAll();
    }

}
