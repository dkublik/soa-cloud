package pl.dk.soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class End2EndApplication {

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.messageConverters(new StringHttpMessageConverter(), new MappingJackson2HttpMessageConverter())
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(End2EndApplication.class, args);
	}
}
