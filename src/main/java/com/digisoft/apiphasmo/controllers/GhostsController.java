package com.digisoft.apiphasmo.controllers;

import com.digisoft.apiphasmo.messages.GhostsApiResponse;
import com.digisoft.apiphasmo.models.GhostsModel;
import com.digisoft.apiphasmo.services.GhostsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/ghost")
public class GhostsController {
    @Autowired
    private GhostsService ghostsService;

    @PostMapping
    public ResponseEntity<GhostsApiResponse> postGhost(@RequestBody @Valid GhostsModel ghost) {
        GhostsModel savedGhost = ghostsService.postGhost(ghost);

        GhostsApiResponse response = new GhostsApiResponse();
        response.setMessage("Ghost created successfully.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setCreatedGhost(savedGhost);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{idGhost}")
    public ResponseEntity<GhostsApiResponse> getGhostById(@PathVariable("idGhost") Long idGhost) {
        Optional<GhostsModel> ghost = ghostsService.getGhostById(idGhost);

        GhostsApiResponse response = new GhostsApiResponse();
        if (ghost.isPresent()) {
            response.setMessage("Ghost found.");
            response.setStatus(HttpStatus.OK.value());
            response.setGetGhost(ghost.get());
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Ghost not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<GhostsApiResponse> getAllGhosts(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "idGhost") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination) {

        Page<GhostsModel> ghostPage = ghostsService.getAllGhosts(page, size, sortBy, sortDir, enablePagination);

        GhostsApiResponse response = new GhostsApiResponse();
        response.setMessage("Ghosts fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setGhostsPage(ghostPage);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "{idGhost}")
    public ResponseEntity<GhostsApiResponse> updateGhostById(@RequestBody @Valid GhostsModel request, @PathVariable Long idGhost) {
        GhostsApiResponse response = new GhostsApiResponse();
        GhostsModel updatedGhost = ghostsService.updateGhostById(request, idGhost);
        if (updatedGhost != null) {
            response.setMessage("Ghost updated successfully.");
            response.setStatus(HttpStatus.OK.value());
            response.setUpdatedGhost(updatedGhost);
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Ghost not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping(path = "/{idGhost}")
    public ResponseEntity<GhostsApiResponse> deleteGhost(@PathVariable("idGhost") Long idGhost) {
        GhostsApiResponse response = new GhostsApiResponse();
        ghostsService.deleteGhostById(idGhost);
        response.setMessage("Ghost deleted successfully.");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
