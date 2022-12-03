package com.cursos.app.service;

import com.cursos.app.dto.ProvinciaDTO;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.util.IGenericCrud;

import java.util.Map;

public interface IProvinciaService extends IGenericCrud<ProvinciaDTO> {

    public Paginacion obtenerProvincias(Map<String,Object> parameters);

}
