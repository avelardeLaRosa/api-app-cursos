package com.cursos.app.dto;

import com.cursos.app.entities.CarreraEntity;
import com.cursos.app.entities.DetalleMatriculaEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO extends AuditoryDTO{

    private int id;
    private String code;
    private String descripcion;
    private int creditos;
    private Double precio;
    private String carrera;
    //private List<DetalleMatriculaDTO> detalles = new ArrayList<>();

}
