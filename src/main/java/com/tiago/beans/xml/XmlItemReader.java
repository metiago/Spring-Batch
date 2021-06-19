package com.tiago.beans.xml;


import java.util.logging.Logger;

import com.tiago.entity.Animal;
import com.tiago.entity.Animals;

import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

@Component
public class XmlItemReader {

    private static final Logger LOGGER = Logger.getLogger("XmlItemReader.class");

    @Autowired
    private ResourceLoader resourceLoader;

    public StaxEventItemReader<Animal> read() throws Exception {

        LOGGER.info("** READING XML FILE! **");

        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(Animals.class);

        Resource resource = resourceLoader.getResource("file:"+System.getProperty("user.dir")+"/data/animals.xml");

        return new StaxEventItemReaderBuilder<Animal>().name("animals")
                .resource(resource)
                .addFragmentRootElements("animal")
                .strict(true)
                .unmarshaller(jaxb2Marshaller).build();
    }
}
