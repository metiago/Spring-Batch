package com.tiago.beans.flat;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Component;

import com.tiago.entity.Animal;
import java.util.logging.Logger;

@Component
public class FlatItemWriter {

    private static final Logger LOGGER = Logger.getLogger("FlatItemWriter.class");

    public ItemWriter<Animal> writer(DataSource dataSource) throws Exception {

        LOGGER.info("** WRITING FLAT FILE! **");

        JdbcBatchItemWriter<Animal> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO ANIMAL (id, aid, scientificName, status, veterinarian, created) "
                + "                VALUES (:id, :aid, :scientificName, :status, :veterinarian, :created)");
        writer.setDataSource(dataSource);       
        writer.setAssertUpdates(true);
        writer.afterPropertiesSet();
        
        return writer;
    }
}
