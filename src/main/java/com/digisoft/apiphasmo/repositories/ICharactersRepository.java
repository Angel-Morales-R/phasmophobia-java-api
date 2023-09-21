package com.digisoft.apiphasmo.repositories;

import com.digisoft.apiphasmo.models.CharactersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharactersRepository extends JpaRepository<CharactersModel, Long> {
}
