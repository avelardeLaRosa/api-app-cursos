package com.cursos.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO extends AuditoryDTO{

    private int id;
    private String nombres;
    private String apellidos;
    private String dni;
    private String email;
    private String telefono;
    private String password;
    private String distrito;
    private String rol;


}
