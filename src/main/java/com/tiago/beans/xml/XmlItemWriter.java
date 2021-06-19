package com.tiago.beans.xml;

import java.util.List;
import java.util.logging.Logger;

import com.tiago.entity.Animal;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class XmlItemWriter implements ItemWriter<Animal>{

    private static final Logger LOGGER = Logger.getLogger("XmlItemWriter.class");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void write(List<? extends Animal> items) throws Exception {
        
        LOGGER.info("** WRITING XML FILE! **");

        final String SQL = "INSERT INTO ANIMAL (id, aid, scientificName, status, veterinarian, created) VALUES (?, ?, ?, ?, ?, ?)";

        for(Animal a : items) { 
            jdbcTemplate.update(SQL, a.getId(), a.getAid(), a.getScientificName(), a.getStatus(), a.getVeterinarian(), a.getCreated());
        }
    }
}
