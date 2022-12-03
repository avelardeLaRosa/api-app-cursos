package com.cursos.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistritoResponse {

    private int id;
    private String descripcion;
    private int provincia;
}
