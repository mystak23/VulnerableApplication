package net.codejava;

import net.codejava.Database.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class VulnerableApplication {

public static void main(String[] args) throws IOException, InterruptedException {
	SpringApplication.run(VulnerableApplication.class, args);
	}

}
