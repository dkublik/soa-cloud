package pl.dk.soa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
public class BasicDiscoveryClient {

    @Autowired
    DiscoveryClient discoveryClient;

    public String getHost(String host) {
        return discoveryClient.getInstances(host).get(0).getUri().toString();
    }

}
