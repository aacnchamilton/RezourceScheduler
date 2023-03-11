package com.rezourcesched.rez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.rezourcesched.rez.ComponentScanMarker;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
//@ComponentScan(basePackages = {"com.rezourcesched.rez", "com.rezourcesched.rez.entity"})
public class RezMain {

  public static void main(String[] args) {
    
    SpringApplication.run(RezMain.class, args);

  }

}
