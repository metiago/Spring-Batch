package com.tiago.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tiago.entity.Animal;

public class AnimalRowMapper implements RowMapper<Animal> {

  public static final String ID_COLUMN = "id";
  public static final String NAME_COLUMN = "scientificName";
  public static final String STATUS = "status";
  private static final String VETERINARIAN = "veterinarian";
  private static final String CREATED = "created";

  public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {

    Animal animal = new Animal();

    animal.setId(rs.getInt(ID_COLUMN));
    animal.setScientificName(rs.getString(NAME_COLUMN));
    animal.setStatus(rs.getString(STATUS));
    animal.setVeterinarian(rs.getString(VETERINARIAN));
    animal.setCreated(rs.getDate(CREATED));

    return animal;
  }
}