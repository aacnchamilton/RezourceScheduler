package com.rezourcesched.rez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rezourcesched.rez"})
public class RezMain {

  public static void main(String[] args) {
    
    SpringApplication.run(RezMain.class, args);

  }

}
