package com.cursos.app.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_carreras")
public class CarreraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private int id;

    @Column(name = "descripcion")
    private String descripcion;


}
