package cinesquare.cinesquare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CinesquareApplication
//        extends SpringBootServletInitializer
{

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(CinesquareApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(CinesquareApplication.class, args);
    }

}
