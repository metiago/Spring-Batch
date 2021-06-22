package com.tiago.beans.xml;

import com.tiago.entity.Animal;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Component
public class XmlItemWriter implements ItemWriter<Animal> {

    private static final Logger LOGGER = Logger.getLogger("XmlItemWriter.class");

    @Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void write(List<? extends Animal> items) throws Exception {

        LOGGER.info("** WRITING XML FILE! **");

        final String SQL = "INSERT INTO ANIMAL (id, aid, scientificName, status, veterinarian, created) VALUES (?, ?, ?, ?, ?, ?)";

        for (int j = 0; j < items.size(); j += items.size()) {

            jdbcTemplate.batchUpdate(SQL,

                    new BatchPreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            Animal animal = items.get(i);
                            ps.setInt(1, animal.getId());
                            ps.setInt(2, animal.getAid());
                            ps.setString(3, animal.getScientificName());
                            ps.setString(4, animal.getStatus());
                            ps.setString(5, animal.getVeterinarian());
                            ps.setDate(6, new java.sql.Date(animal.getCreated().getTime()));
                        }

                        @Override
                        public int getBatchSize() {
                            return items.size();
                        }
                    });
        }
    }
}
