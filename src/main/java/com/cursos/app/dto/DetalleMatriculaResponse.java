package com.cursos.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleMatriculaResponse {

    private int id;
    private int cantidad;
    private double total;
    private String curso;
}
