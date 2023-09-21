package com.digisoft.apiphasmo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "ghosts")
public class GhostsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGhost;

    //NOTA, YA NO ES NECESARIO USAR COLUMN, PARSEA AUTOMATICAMENTE A CAMELCASE
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Type s is required")
    private String type;

    @NotBlank(message = "Speed is required")
    private String speed;

    @NotBlank(message = "Description is required")
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "lastUpdate is required")
    private LocalDate lastUpdate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ghost_testings", joinColumns = @JoinColumn(name = "id_Ghost", referencedColumnName = "idGhost"),
                inverseJoinColumns = @JoinColumn(name = "id_testing", referencedColumnName = "idTesting")
    )
    @JsonManagedReference
    private List<TestingsModel> testingsModels;
}
