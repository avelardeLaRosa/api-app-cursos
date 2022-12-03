package com.cursos.app.entities;

import com.cursos.app.util.AuditoryEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "t_cursos")
public class CursoEntity extends AuditoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "creditos")
    private int creditos;
    @Column(name = "precio")
    private Double precio;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private CarreraEntity carrera;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "curso")
    private List<DetalleMatriculaEntity> detalles = new ArrayList<>();
}
