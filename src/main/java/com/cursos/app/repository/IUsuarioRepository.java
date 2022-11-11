package com.cursos.app.repository;

import com.cursos.app.entities.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity,Integer> {

    Optional<UsuarioEntity> findByEmailAndStatus(String email, String status);
    Optional<UsuarioEntity> findOneByEmailAndStatus(String email, String status);
    Optional<UsuarioEntity> findByIdAndStatus(int id, String status);

    @Query(
            value = "select u from UsuarioEntity u " +
                    "where u.status = :status"
    )
    Page<UsuarioEntity> getUsuarioEntities(Pageable pageable, String status);



}
