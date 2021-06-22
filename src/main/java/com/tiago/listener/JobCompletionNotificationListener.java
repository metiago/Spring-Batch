package com.tiago.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tiago.entity.Animal;
import com.tiago.util.AnimalRowMapper;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger LOG = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

            LOG.info("JOB FINISHED");

            List<Animal> results = jdbcTemplate.query("SELECT * FROM ANIMAL", new AnimalRowMapper());

            results.forEach((Animal animal) -> LOG.info("FOUND <" + animal.getScientificName() + "> IN THE DATABASE."));
        }
    }
}
