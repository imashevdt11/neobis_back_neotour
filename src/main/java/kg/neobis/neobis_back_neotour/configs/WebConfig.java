package kg.neobis.neobis_back_neotour.configs;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET");
        registry.addMapping("/api/bookings/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("POST");
    }
}