package com.cursos.app.repository;

import com.cursos.app.entities.CursoEntity;
import com.cursos.app.entities.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICursoRepository extends JpaRepository<CursoEntity,Integer> {

    Optional<CursoEntity> findByDescripcion(String descripcion);

    @Query(
            value = "select c from CursoEntity c " +
                    "where c.status = :status"
    )
    Page<CursoEntity> getCursoEntities(Pageable pageable, String status);

    Optional<CursoEntity> findByIdAndStatus(int id,String status);
}
