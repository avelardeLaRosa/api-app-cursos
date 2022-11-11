package com.cursos.app.rest.request;

import com.cursos.app.dto.AuditoryDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioRequest extends AuditoryDTO {
    private int id;
    private String nombres;
    private String apellidos;
    private String dni;
    private String email;
    private String telefono;
    private String password;
    private int distrito;
    private int rol;
}
