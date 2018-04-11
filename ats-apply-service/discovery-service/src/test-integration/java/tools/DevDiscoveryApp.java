package tools;

import org.springframework.boot.SpringApplication;
import tech.viacom.arc.DiscoveryApp;

public final class DevDiscoveryApp {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(DiscoveryApp.class);
        springApplication.setAdditionalProfiles("dev-local");
        springApplication.run(args);
    }

}
