package com.digisoft.apiphasmo.services;

import com.digisoft.apiphasmo.models.CharactersModel;
import com.digisoft.apiphasmo.repositories.ICharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharactersService {

    @Autowired
    private ICharactersRepository iCharactersRepository;


    public CharactersModel postCharacter(CharactersModel character) {
        return iCharactersRepository.save(character);
    }

    public Page<CharactersModel> getAllCharacters(Integer page, Integer size,String sortBy,String sortDir, Boolean enablePagination) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return iCharactersRepository.findAll(enablePagination ? PageRequest.of(page, size, sort) : Pageable.unpaged());
    }

    public Optional<CharactersModel> getCharacterById(Long idCharacter) {
        return iCharactersRepository.findById(idCharacter);
    }

    public CharactersModel updateCharacterById(CharactersModel request, Long idCharacter) {
        Optional<CharactersModel> characterOptional = iCharactersRepository.findById(idCharacter);

        if (characterOptional.isPresent()) {
            CharactersModel charactersModel = characterOptional.get();
            charactersModel.setName(request.getName());
            charactersModel.setPais(request.getPais());
            charactersModel.setAge(request.getAge());
            charactersModel.setDescription(request.getDescription());
            charactersModel.setGender(request.getGender());
            charactersModel.setLastUpdate(request.getLastUpdate());
            iCharactersRepository.save(charactersModel);
            return charactersModel;
        } else {
            return null;
        }
    }


    public CharactersModel deleteCharacterById(Long idCharacter) {
        Optional<CharactersModel> characterOptional = iCharactersRepository.findById(idCharacter);

        if (characterOptional.isPresent()) {
            CharactersModel characterToDelete = characterOptional.get();
            iCharactersRepository.delete(characterToDelete);
            return characterToDelete;
        } else {
            return null;
        }
    }


}