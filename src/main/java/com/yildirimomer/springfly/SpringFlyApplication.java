package com.yildirimomer.springfly;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@SpringBootApplication
@EnableJSONDoc
@EnableJpaRepositories
public class SpringFlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFlyApplication.class, args);
	}

}
