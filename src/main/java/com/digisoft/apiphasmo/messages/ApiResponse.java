package com.digisoft.apiphasmo.messages;

import com.digisoft.apiphasmo.models.CharactersModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class ApiResponse {
    private String message;
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CharactersModel character;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CharactersModel createdCharacter;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CharactersModel updatedCharacter;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CharactersModel deletedCharacter;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Page<CharactersModel> charactersPage;

}