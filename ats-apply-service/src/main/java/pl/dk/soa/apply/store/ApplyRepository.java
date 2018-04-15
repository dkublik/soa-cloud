package pl.dk.soa.apply.store;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparing;

@Service
public class ApplyRepository {

    private Map<String, StoredApplication> applications = new HashMap<>();

    public void store(StoredApplication application) {
        applications.put(application.getId(), application);
        application.stored();
    }

    public List<StoredApplication> findAll() {
        List<StoredApplication> allApplications = new ArrayList<>(applications.values());
        allApplications.sort(comparing(StoredApplication::getCreatedTime));
        return allApplications;
    }

    public Optional<StoredApplication> find(String id) {
        return Optional.ofNullable(applications.get(id));
    }

}
