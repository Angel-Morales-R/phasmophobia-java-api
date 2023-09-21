package com.digisoft.apiphasmo.controllers;

import com.digisoft.apiphasmo.messages.ApiResponse;
import com.digisoft.apiphasmo.models.CharactersModel;
import com.digisoft.apiphasmo.services.CharactersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/character")
public class CharactersController {

    @Autowired
    private CharactersService charactersService;

    @PostMapping
    public ResponseEntity<ApiResponse> postCharacter(@RequestBody @Valid CharactersModel character) {
        CharactersModel savedCharacter = charactersService.postCharacter(character);

        ApiResponse response = new ApiResponse();
        if (savedCharacter != null) {
            response.setMessage("Character created successfully.");
            response.setStatus(HttpStatus.CREATED.value());
            response.setCreatedCharacter(savedCharacter);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setMessage("Failed to create character.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @GetMapping(path = "/{idCharacter}")
    public ResponseEntity<ApiResponse>  getCharacterById(@PathVariable("idCharacter") Long idCharacter) {
        Optional<CharactersModel> character = charactersService.getCharacterById(idCharacter);

        ApiResponse response = new ApiResponse();
        if (character.isPresent()) {
            response.setMessage("Character found.");
            response.setStatus(HttpStatus.OK.value());
            response.setCharacter(character.get());
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Character not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse>  getAllCharacters(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "idCharacter") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination) {

        Page<CharactersModel> charactersPage = charactersService.getAllCharacters(page, size, sortBy, sortDir, enablePagination);

        ApiResponse response = new ApiResponse();
        response.setMessage("Characters fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setCharactersPage(charactersPage);
        return ResponseEntity.ok(response);
    }


    @PutMapping(path = "{idCharacter}")
    public ResponseEntity<ApiResponse> updateCharacterById(@RequestBody @Valid CharactersModel request, @PathVariable Long idCharacter) {
        ApiResponse response = new ApiResponse();
        CharactersModel updatedCharacter = charactersService.updateCharacterById(request, idCharacter);
        if (updatedCharacter != null) {
            response.setMessage("Character updated successfully.");
            response.setStatus(HttpStatus.OK.value());
            response.setUpdatedCharacter(updatedCharacter);
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Character not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @DeleteMapping(path = "/{idCharacter}")
    public ResponseEntity<ApiResponse> deleteCharacter(@PathVariable("idCharacter") Long idCharacter) {
        ApiResponse response = new ApiResponse();
        CharactersModel deletedCharacter = charactersService.deleteCharacterById(idCharacter);
        if (deletedCharacter != null) {
            response.setMessage("Character deleted successfully.");
            response.setStatus(HttpStatus.OK.value());
            response.setDeletedCharacter(deletedCharacter);
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Character not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }


    }
}
