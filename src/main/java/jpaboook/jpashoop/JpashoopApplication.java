package jpaboook.jpashoop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashoopApplication {

	@Autowired
	 S3Config s3Config;

	public static void main(String[] args) {
		SpringApplication.run(JpashoopApplication.class, args);

	}

}
