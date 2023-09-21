package com.digisoft.apiphasmo.services;

import com.digisoft.apiphasmo.models.GhostsModel;
import com.digisoft.apiphasmo.repositories.IGhostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GhostsService {
    @Autowired
    private IGhostsRepository ghostsRepository;

    public GhostsModel postGhost(GhostsModel ghost) {
        return ghostsRepository.save(ghost);
    }

    public Page<GhostsModel> getAllGhosts(Integer page, Integer size, String sortBy, String sortDir, Boolean enablePagination) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return ghostsRepository.findAll(enablePagination ? PageRequest.of(page, size, sort) : Pageable.unpaged());
    }

    public Optional<GhostsModel> getGhostById(Long idGhost) {
        return ghostsRepository.findById(idGhost);
    }

    public GhostsModel updateGhostById(GhostsModel request, Long idGhost) {
        Optional<GhostsModel> ghostOptional = ghostsRepository.findById(idGhost);

        if (ghostOptional.isPresent()) {
            GhostsModel ghost = ghostOptional.get();
            ghost.setName(request.getName());
            ghost.setType(request.getType());
            ghost.setSpeed(request.getSpeed());
            ghost.setDescription(request.getDescription());
            ghost.setLastUpdate(request.getLastUpdate());
            ghost.setTestingsModels(request.getTestingsModels());
            return ghostsRepository.save(ghost);
        } else {
            return null;
        }
    }


    public GhostsModel deleteGhostById(Long idGhost) {
        Optional<GhostsModel> ghostOptional = ghostsRepository.findById(idGhost);

        if (ghostOptional.isPresent()) {
            GhostsModel ghostToDelete = ghostOptional.get();
            ghostsRepository.delete(ghostToDelete);
            return ghostToDelete;
        } else {
            return null;
        }
    }

}
