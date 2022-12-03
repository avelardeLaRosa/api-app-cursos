package com.cursos.app.repository;

import com.cursos.app.entities.DetalleMatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetalleMatriculaRepository extends JpaRepository<DetalleMatriculaEntity,Integer> {
}
