import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://cinesquare.yahmedora.com:9096")
                .allowCredentials(true)
                .allowedHeaders(String.valueOf(Arrays.asList("Content-Type", "Authorization", "Accept")))
                .allowedMethods("GET", "POST");
    }
}
