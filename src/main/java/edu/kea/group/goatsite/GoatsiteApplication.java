package edu.kea.group.goatsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GoatsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoatsiteApplication.class, args);
        //BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        //System.out.println(bcpe.encode("Hunter2"));
    }

}
