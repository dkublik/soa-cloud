package pl.dk.soa.apply.resourcev4;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.dk.soa.apply.store.StoredApplication;

@Service
public class ApplicationApprover {

    @Async
    public void approve(StoredApplication application) throws InterruptedException {
        Thread.sleep(2000);
        application.accepted();
    }

}
