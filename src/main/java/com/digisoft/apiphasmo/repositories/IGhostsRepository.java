package com.digisoft.apiphasmo.repositories;

import com.digisoft.apiphasmo.models.GhostsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGhostsRepository extends JpaRepository<GhostsModel, Long> {
}
