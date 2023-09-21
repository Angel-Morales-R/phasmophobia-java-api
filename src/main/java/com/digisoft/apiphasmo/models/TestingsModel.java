package com.digisoft.apiphasmo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "testings")
public class TestingsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTesting;

    //NOTA, YA NO ES NECESARIO USAR COLUMN, PARSEA AUTOMATICAMENTE A CAMELCASE
    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Price is required")
    private String price;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "lastUpdate is required")
    private LocalDate lastUpdate;

    @ManyToMany(mappedBy = "testingsModels", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<GhostsModel> ghostModels;
}
