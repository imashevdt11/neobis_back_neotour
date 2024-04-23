package kg.neobis.neobis_back_neotour.configs;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("https://neobis-back-neotour-d00d4638f0fd.herokuapp.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}