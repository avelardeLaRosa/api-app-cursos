package com.cursos.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistritoDTO {

    private int id;
    private String descripcion;
    private String provincia;
    private List<UsuarioDTO> usuarios = new ArrayList<>();

}
