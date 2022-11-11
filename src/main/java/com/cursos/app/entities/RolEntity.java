package com.cursos.app.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_roles")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private int id;
    @Column(name = "descripcion")
    private String descripcion;
}
