package com.cursos.app.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "t_distritos")
public class DistritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_distrito")
    private int id;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia")
    private ProvinciaEntity provincia;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "distrito",orphanRemoval = true)
    private List<UsuarioEntity> usuarios = new ArrayList<>();
}
