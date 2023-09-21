package com.digisoft.apiphasmo.repositories;

import com.digisoft.apiphasmo.models.MapsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMapsRepository extends JpaRepository<MapsModel, Long> {
}
