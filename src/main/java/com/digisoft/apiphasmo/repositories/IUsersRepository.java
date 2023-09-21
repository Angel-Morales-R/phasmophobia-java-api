package com.digisoft.apiphasmo.repositories;

import com.digisoft.apiphasmo.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<UsersModel, Long> {

    Optional<UsersModel> findOneByEmail(String email);
}
