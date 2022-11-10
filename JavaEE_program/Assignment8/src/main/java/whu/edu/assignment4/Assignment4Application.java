package whu.edu.assignment4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class Assignment4Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment4Application.class, args);
    }

}
