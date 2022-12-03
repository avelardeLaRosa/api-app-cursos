package com.cursos.app.repository;

import com.cursos.app.entities.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMatriculaRepository extends JpaRepository<MatriculaEntity,Integer> {

    Optional<MatriculaEntity> findByCodeAndStatus(String code,String status);

}
