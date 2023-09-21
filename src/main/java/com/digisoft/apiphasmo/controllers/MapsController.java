package com.digisoft.apiphasmo.controllers;

import com.digisoft.apiphasmo.messages.MapsApiResponse;
import com.digisoft.apiphasmo.models.MapsModel;
import com.digisoft.apiphasmo.services.MapsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/map")
public class MapsController {
    
    @Autowired
    private MapsService mapsService;

    @PostMapping
    public ResponseEntity<MapsApiResponse> postMap(@RequestBody @Valid MapsModel map) {
        MapsModel savedMap = mapsService.postMap(map);

        MapsApiResponse response = new MapsApiResponse();
        if (savedMap != null) {
            response.setMessage("Map created successfully.");
            response.setStatus(HttpStatus.CREATED.value());
            response.setCreatedMap(savedMap);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setMessage("Failed to create map.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @GetMapping(path = "/{idMap}")
    public ResponseEntity<MapsApiResponse>  getMapById(@PathVariable("idMap") Long idMap) {
        Optional<MapsModel> map = mapsService.getMapById(idMap);

        MapsApiResponse response = new MapsApiResponse();
        if (map.isPresent()) {
            response.setMessage("Map found.");
            response.setStatus(HttpStatus.OK.value());
            response.setMap(map.get());
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Map not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<MapsApiResponse>  getAllMaps(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "idMap") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination) {

        Page<MapsModel> mapPage = mapsService.getAllMaps(page, size, sortBy, sortDir, enablePagination);

        MapsApiResponse response = new MapsApiResponse();
        response.setMessage("Map fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setMapsPage(mapPage);
        return ResponseEntity.ok(response);
    }


    @PutMapping(path = "{idMap}")
    public ResponseEntity<MapsApiResponse> updateMapById(@RequestBody @Valid MapsModel request, @PathVariable Long idMap) {
        MapsApiResponse response = new MapsApiResponse();
        MapsModel updatedMap = mapsService.updateMapById(request, idMap);
        if (updatedMap != null) {
            response.setMessage("Map updated successfully.");
            response.setStatus(HttpStatus.OK.value());
            response.setUpdatedMap(updatedMap);
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Map not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @DeleteMapping(path = "/{idMap}")
    public ResponseEntity<MapsApiResponse> deleteMap(@PathVariable("idMap") Long idMap) {
        MapsApiResponse response = new MapsApiResponse();
        MapsModel deletedMap = mapsService.deleteMapById(idMap);
        if (deletedMap != null) {
            response.setMessage("Map deleted successfully.");
            response.setStatus(HttpStatus.OK.value());
            response.setDeletedMap(deletedMap);
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Map not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }


    }
}
