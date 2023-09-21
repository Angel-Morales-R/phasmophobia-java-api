package com.digisoft.apiphasmo.controllers;

import com.digisoft.apiphasmo.messages.TestingsApiResponse;
import com.digisoft.apiphasmo.models.TestingsModel;
import com.digisoft.apiphasmo.services.TestingsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/testing")
public class TestingsController {
    @Autowired
    private TestingsService testingsService;

    @PostMapping
    public ResponseEntity<TestingsApiResponse> postTesting(@RequestBody @Valid TestingsModel testing) {
        TestingsModel savedTesting = testingsService.postTesting(testing);

        TestingsApiResponse response = new TestingsApiResponse();
        response.setMessage("Testing created successfully.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setCreatedTesting(savedTesting);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{idTesting}")
    public ResponseEntity<TestingsApiResponse> getTestingById(@PathVariable("idTesting") Long idTesting) {
        Optional<TestingsModel> testing = testingsService.getTestingById(idTesting);

        TestingsApiResponse response = new TestingsApiResponse();
        if (testing.isPresent()) {
            response.setMessage("Testing found.");
            response.setStatus(HttpStatus.OK.value());
            response.setGetTesting(testing.get());
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Testing not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<TestingsApiResponse> getAllTestings(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "idTesting") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination) {

        Page<TestingsModel> testingPage = testingsService.getAllTestings(page, size, sortBy, sortDir, enablePagination);

        TestingsApiResponse response = new TestingsApiResponse();
        response.setMessage("Testings fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setTestingsPage(testingPage);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "{idTesting}")
    public ResponseEntity<TestingsApiResponse> updateTestingById(@RequestBody @Valid TestingsModel request, @PathVariable Long idTesting) {
        TestingsApiResponse response = new TestingsApiResponse();
        TestingsModel updatedTesting = testingsService.updateTestingById(request, idTesting);
        if (updatedTesting != null) {
            response.setMessage("Testing updated successfully.");
            response.setStatus(HttpStatus.OK.value());
            response.setUpdatedTesting(updatedTesting);
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Testing not found.");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping(path = "/{idTesting}")
    public ResponseEntity<TestingsApiResponse> deleteTesting(@PathVariable("idTesting") Long idTesting) {
        TestingsApiResponse response = new TestingsApiResponse();
        testingsService.deleteTestingById(idTesting);
        response.setMessage("Testing deleted successfully.");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
