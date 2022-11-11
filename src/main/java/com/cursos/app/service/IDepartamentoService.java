package com.cursos.app.service;

import com.cursos.app.dto.DepartamentoDTO;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.util.IGenericCrud;

public interface IDepartamentoService extends IGenericCrud<DepartamentoDTO> {

    public Paginacion obtenerDepartamentos(int pageNum, int pageSize, String orderBy, String sortDir);

}
