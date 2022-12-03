package com.cursos.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoRequest {

    private int id;
    private String code;
    private String descripcion;
    private int creditos;
    private Double precio;
    private int carrera;
}
