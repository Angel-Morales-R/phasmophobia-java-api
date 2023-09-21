package com.digisoft.apiphasmo.repositories;

import com.digisoft.apiphasmo.models.TestingsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestingsRepository extends JpaRepository<TestingsModel, Long> {
}
