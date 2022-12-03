package com.cursos.app.entities;

import com.cursos.app.util.AuditoryEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_detalle_matricula")
public class DetalleMatriculaEntity extends AuditoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private int id;

    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "precio")
    private double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private CursoEntity curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matricula")
    private MatriculaEntity matricula;


}
