package com.digisoft.apiphasmo.services;

import com.digisoft.apiphasmo.models.TestingsModel;
import com.digisoft.apiphasmo.repositories.ITestingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestingsService {
    @Autowired
    private ITestingsRepository testingsRepository;

    public TestingsModel postTesting(TestingsModel testing) {
        return testingsRepository.save(testing);
    }

    public Page<TestingsModel> getAllTestings(Integer page, Integer size, String sortBy, String sortDir, Boolean enablePagination) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return testingsRepository.findAll(enablePagination ? PageRequest.of(page, size, sort) : Pageable.unpaged());
    }

    public Optional<TestingsModel> getTestingById(Long idTesting) {
        return testingsRepository.findById(idTesting);
    }

    public TestingsModel updateTestingById(TestingsModel request, Long idTesting) {
        Optional<TestingsModel> testingOptional = testingsRepository.findById(idTesting);

        if (testingOptional.isPresent()) {
            TestingsModel testing = testingOptional.get();
            testing.setType(request.getType());
            testing.setPrice(request.getPrice());
            testing.setLastUpdate(request.getLastUpdate());
            testing.setGhostModels(request.getGhostModels());
            return testingsRepository.save(testing);
        } else {
            return null;
        }
    }

    public TestingsModel deleteTestingById(Long idGhost) {
        Optional<TestingsModel> testingOptional = testingsRepository.findById(idGhost);

        if (testingOptional.isPresent()) {
            TestingsModel testingToDelete = testingOptional.get();
            testingsRepository.delete(testingToDelete);
            return testingToDelete;
        } else {
            return null;
        }

    }
}
