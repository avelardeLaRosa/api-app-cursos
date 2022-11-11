package com.cursos.app.repository;

import com.cursos.app.entities.DistritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistritoRepository extends JpaRepository<DistritoEntity,Integer> {
}
