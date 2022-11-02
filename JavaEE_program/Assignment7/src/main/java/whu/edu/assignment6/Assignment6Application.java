package whu.edu.assignment6;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
@MapperScan("whu.edu.assignment6.dao")
public class Assignment6Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment6Application.class, args);
	}

}
