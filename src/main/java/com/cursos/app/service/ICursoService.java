package com.cursos.app.service;

import com.cursos.app.dto.CursoDTO;
import com.cursos.app.dto.CursoRequest;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.util.IGenericCrud;
import com.cursos.app.util.IGenericCrud2;

import java.util.Map;

public interface ICursoService extends IGenericCrud2<CursoDTO, CursoRequest> {

    public Paginacion obtenerCursos(Map<String,Object> parameters);

}
