package com.tiago.beans.flat;

import com.tiago.entity.Animal;
import com.tiago.util.AnimalFieldSetMapper;
import com.tiago.util.BlankLineRecordSeparatorPolicy;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class FlatItemReader {

    private static final Logger LOGGER = Logger.getLogger("FlatItemReader.class");

    private FlatFileItemReader<Animal> itemReader;

    public ItemReader<Animal> read() throws Exception {

        LOGGER.info("** READING FLAT FILE! **");

        if (itemReader == null) {

            itemReader = new FlatFileItemReader<>();
            itemReader.setResource(new FileSystemResource("data/animals.txt"));
            itemReader.setLinesToSkip(1);
            DefaultLineMapper<Animal> lineMapper = new DefaultLineMapper<>();
            FixedLengthTokenizer lineTokenizer = new FixedLengthTokenizer();
            lineTokenizer.setNames(new String[]{"ID", "scientificName", "status", "veterinarian", "created"});
            lineTokenizer.setColumns(new Range[]{new Range(1, 2), new Range(3, 32), new Range(33, 39), new Range(43, 50), new Range(93, 100)});
            lineMapper.setLineTokenizer(lineTokenizer);
            lineMapper.setFieldSetMapper(new AnimalFieldSetMapper());
            itemReader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());
            itemReader.setLineMapper(lineMapper);
        }

        return itemReader;
    }
}
