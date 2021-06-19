package com.tiago.beans.xml;

import java.util.logging.Logger;

import com.tiago.entity.Animal;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class XmlItemProcessor implements ItemProcessor<Animal, Animal> {

    private static final Logger LOGGER = Logger.getLogger("XmlItemProcessor.class");

    @Override
    public Animal process(Animal animal) throws Exception {

        LOGGER.info("** PROCESSING XML FILE! **");

        if(animal.getStatus().equals("BAD")) {
            animal.setStatus("XML - BAD");
        }
        
        return animal;
    }
}
