package com.cursos.app.repository;

import com.cursos.app.entities.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<RolEntity,Integer> {
}
