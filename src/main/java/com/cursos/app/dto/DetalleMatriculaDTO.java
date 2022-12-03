package com.cursos.app.dto;

import com.cursos.app.entities.CursoEntity;
import com.cursos.app.entities.MatriculaEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleMatriculaDTO extends AuditoryDTO{


    private int id;
    private String code;
    private int cantidad;
    private double total;
    private String curso;

}
