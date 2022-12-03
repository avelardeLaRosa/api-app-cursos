package com.cursos.app.repository;

import com.cursos.app.entities.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMatriculaRepository extends JpaRepository<MatriculaEntity,Integer> {
}
