package com.cursos.app.service;

import com.cursos.app.dto.CarreraDTO;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.util.IGenericCrud;

import java.util.Map;

public interface ICarreraService extends IGenericCrud<CarreraDTO> {

    public Paginacion obtenerCarreras(Map<String,Object> parameters);

}
