package com.cursos.app.repository;

import com.cursos.app.entities.CarreraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICarreraRepository extends JpaRepository<CarreraEntity,Integer> {


    Optional<CarreraEntity> findByDescripcion(String descripcion);
}
