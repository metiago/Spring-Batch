//package com.tiago.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import com.tiago.beans.flat.FlatItemProcessor;
//import com.tiago.beans.flat.FlatItemReader;
//import com.tiago.beans.flat.FlatItemWriter;
//import com.tiago.listener.JobCompletionNotificationListener;
//import com.tiago.entity.Animal;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//
//@Configuration
//public class FlatFileConfiguration {
//
//   @Value("${spring.datasource.jdbcUrl}")
//   private String url;
//
//   @Value("${spring.datasource.driverClassName}")
//   private String driver;
//
//   @Value("${spring.datasource.username}")
//   private String username;
//
//   @Value("${spring.datasource.password}")
//   private String password;
//
//   @Autowired
//   private JobBuilderFactory jobBuilders;
//
//   @Autowired
//   private StepBuilderFactory stepBuilders;
//
//   @Autowired
//   private FlatItemReader flatItemReader;
//
//   @Autowired
//   private FlatItemWriter flatItemWriter;
//
//   @Bean
//   public ItemProcessor<Animal, Animal> processor() {
//       return new FlatItemProcessor();
//   }
//
//   @Bean
//   public Job importUserJob(JobCompletionNotificationListener listener) throws Exception {
//       return jobBuilders.get("flatFiles").listener(listener)
//               .incrementer(new RunIdIncrementer())
//               .flow(step1())
//               .end().build();
//   }
//
//   @Bean
//   public Step step1() throws Exception {
//
//       return stepBuilders.get("step1").<Animal, Animal>chunk(1000).reader(flatItemReader.read())
//               .faultTolerant()
//               .skipLimit(1)
//               .skip(NullPointerException.class)
//               .processor(processor())
//               .faultTolerant()
//               .skipLimit(1)
//               .skip(NullPointerException.class)
//               .writer(flatItemWriter.writer(getDataSource()))
//               .faultTolerant()
//               .skipLimit(1)
//               .skip(NullPointerException.class)
//               .build();
//   }
//
//   @Bean
//   public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//       return new JdbcTemplate(dataSource);
//   }
//
//   @Bean
//   public DataSource getDataSource() {
//
//       DriverManagerDataSource dataSource = new DriverManagerDataSource();
//       dataSource.setDriverClassName(driver);
//       dataSource.setUrl(url);
//       dataSource.setUsername(username);
//       dataSource.setPassword(password);
//
//       return dataSource;
//   }
//}
