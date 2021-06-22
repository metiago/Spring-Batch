package com.tiago;

import com.tiago.config.DatasourceConfiguration;
import com.tiago.config.XmlFileConfiguration;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableBatchProcessing
@Import({DatasourceConfiguration.class, XmlFileConfiguration.class})
@ComponentScan({"com.tiago.*"})
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

        System.exit(0);
    }
}
