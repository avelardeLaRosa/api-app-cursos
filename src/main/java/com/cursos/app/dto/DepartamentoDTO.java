package com.cursos.app.dto;

import com.cursos.app.entities.ProvinciaEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartamentoDTO {

    private int id;
    private String descripcion;
    //private List<ProvinciaDTO> provincia = new ArrayList<>();
}
