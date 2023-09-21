package com.digisoft.apiphasmo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "characters")
public class CharactersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCharacter;

    //NOTA, YA NO ES NECESARIO USAR COLUMN, PARSEA AUTOMATICAMENTE A CAMELCASE
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Pais is required")
    private String pais;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Age is required")
    private Integer age;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "lastUpdate is required")
    private LocalDate lastUpdate;


}
