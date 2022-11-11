package com.cursos.app.repository;

import com.cursos.app.entities.ProvinciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinciaRepository extends JpaRepository<ProvinciaEntity,Integer> {
}
