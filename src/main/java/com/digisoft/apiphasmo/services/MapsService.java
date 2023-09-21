package com.digisoft.apiphasmo.services;

import com.digisoft.apiphasmo.models.MapsModel;
import com.digisoft.apiphasmo.repositories.IMapsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MapsService {

    @Autowired
    private IMapsRepository iMapsRepository;

    public MapsModel postMap(MapsModel map) {
        return iMapsRepository.save(map);
    }

    public Page<MapsModel> getAllMaps(Integer page, Integer size, String sortBy, String sortDir, Boolean enablePagination) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return iMapsRepository.findAll(enablePagination ? PageRequest.of(page, size, sort) : Pageable.unpaged());
    }

    public Optional<MapsModel> getMapById(Long idMap) {
        return iMapsRepository.findById(idMap);
    }

    public MapsModel updateMapById(MapsModel request, Long idMap) {
        Optional<MapsModel> mapOptional = iMapsRepository.findById(idMap);

        if (mapOptional.isPresent()) {
            MapsModel mapsModel = mapOptional.get();
            mapsModel.setName(request.getName());
            mapsModel.setSize(request.getSize());
            mapsModel.setRooms(request.getRooms());
            mapsModel.setFloors(request.getFloors());
            mapsModel.setDescription(request.getDescription());
            mapsModel.setLastUpdate(request.getLastUpdate());
            iMapsRepository.save(mapsModel);
            return mapsModel;
        } else {
            return null;
        }
    }


    public MapsModel deleteMapById(Long idMap) {
        Optional<MapsModel> mapOptional = iMapsRepository.findById(idMap);

        if (mapOptional.isPresent()) {
            MapsModel mapToDelete = mapOptional.get();
            iMapsRepository.delete(mapToDelete);
            return mapToDelete;
        } else {
            return null;
        }
    }

}
