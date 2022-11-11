package com.cursos.app.repository;

import com.cursos.app.entities.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentoRepository extends JpaRepository<DepartamentoEntity,Integer> {
}
