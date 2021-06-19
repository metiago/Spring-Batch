package com.tiago.util;

import com.tiago.entity.Animal;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class AnimalFieldSetMapper implements FieldSetMapper<Animal> {

    @Override
    public Animal mapFieldSet(FieldSet fieldSet) {
        Animal animal = new Animal();
        animal.setId(fieldSet.readInt("ID"));
        animal.setScientificName(fieldSet.readString("scientificName"));
        animal.setStatus(fieldSet.readString("status"));
        animal.setVeterinarian(fieldSet.readString("veterinarian"));
        animal.setCreated(fieldSet.readDate("created", "yyyyMMdd"));
        return animal;
    }
}
