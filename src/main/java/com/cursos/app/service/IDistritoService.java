package com.cursos.app.service;

import com.cursos.app.dto.DistritoDTO;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.util.IGenericCrud;

public interface IDistritoService extends IGenericCrud<DistritoDTO> {

    public Paginacion obtenerDistritos(int pageNum, int pageSize, String orderBy, String sortDir);

}
