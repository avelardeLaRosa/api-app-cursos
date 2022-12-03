package com.cursos.app.dto;

import com.cursos.app.entities.DetalleMatriculaEntity;
import com.cursos.app.entities.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatriculaDTO extends AuditoryDTO {

    private int id;

    private String code;
    private int usuario;

    private List<DetalleMatriculaDTO> detalles = new ArrayList<>();
}
