package com.cursos.app.entities;

import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.util.AuditoryEntity;
import com.cursos.app.util.MapDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "t_usuarios")
public class UsuarioEntity extends AuditoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private int id;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "dni")
    private String dni;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_distrito")
    private DistritoEntity distrito;


    @OneToOne(orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private RolEntity rol;


    
}
