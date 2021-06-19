package com.tiago;

import com.tiago.config.XmlFileConfiguration;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan({"com.tiago.*"})
@Import({XmlFileConfiguration.class})
@EnableBatchProcessing
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

        System.exit(0);
    }
}
