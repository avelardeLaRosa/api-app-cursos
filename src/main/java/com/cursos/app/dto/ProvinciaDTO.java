package com.cursos.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProvinciaDTO {

    private int id;
    private String descripcion;
    private String departamento;
    //private List<DistritoDTO> distritos = new ArrayList<>();
}
