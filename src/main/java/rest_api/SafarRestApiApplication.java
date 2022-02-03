package rest_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafarRestApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SafarRestApiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Application is ready.........");
    }
}
