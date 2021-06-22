package com.tiago.config;

import com.tiago.beans.xml.XmlItemProcessor;
import com.tiago.beans.xml.XmlItemReader;
import com.tiago.beans.xml.XmlItemWriter;
import com.tiago.entity.Animal;
import com.tiago.listener.JobCompletionNotificationListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlFileConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Autowired
    private XmlItemReader itemReader;

    @Autowired
    private XmlItemWriter itemWriter;

    @Bean
    public ItemProcessor<Animal, Animal> processor() {
        return new XmlItemProcessor();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) throws Exception {
        return jobBuilders.get("xmlFiles").listener(listener)
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end().build();
    }

    @Bean
    public Step step1() throws Exception {

        return stepBuilders.get("step1").<Animal, Animal>chunk(1000).reader(itemReader.read())
                .faultTolerant()
                .skipLimit(1)
                .skip(NullPointerException.class)
                .processor(processor())
                .faultTolerant()
                .skipLimit(1)
                .skip(NullPointerException.class)
                .writer(itemWriter)
                .faultTolerant()
                .skipLimit(1)
                .skip(NullPointerException.class)
                .build();
    }
}
