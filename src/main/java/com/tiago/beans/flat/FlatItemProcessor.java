package com.tiago.beans.flat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.tiago.entity.Animal;
import java.util.logging.Logger;

@Component
public class FlatItemProcessor implements ItemProcessor<Animal, Animal> {

    private static final Logger LOGGER = Logger.getLogger("FlatItemProcessor.class");

    @Override
    public Animal process(Animal animal) throws Exception {

        LOGGER.info("** PROCESSING FLAT FILE! **");
        
        if(animal.getStatus().trim().equalsIgnoreCase("GOOD")) {
            return animal;
        }

        return null;
    }
}
