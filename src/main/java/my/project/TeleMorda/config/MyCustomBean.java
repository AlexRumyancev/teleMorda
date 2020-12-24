package my.project.TeleMorda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyCustomBean {

    @Bean
    public RestTemplate initRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
