package com.digisoft.apiphasmo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "maps")
public class MapsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMap;

    //NOTA, YA NO ES NECESARIO USAR COLUMN, PARSEA AUTOMATICAMENTE A CAMELCASE
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Size is required")
    private String size;

    @NotBlank(message = "rooms is required")
    private String rooms;

    @NotBlank(message = "Floors is required")
    private String floors;

    @NotBlank(message = "Description is required")
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "lastUpdate is required")
    private LocalDate lastUpdate;

}
