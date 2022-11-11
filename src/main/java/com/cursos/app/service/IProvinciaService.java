package com.cursos.app.service;

import com.cursos.app.dto.ProvinciaDTO;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.util.IGenericCrud;

public interface IProvinciaService extends IGenericCrud<ProvinciaDTO> {

    public Paginacion obtenerProvincias(int pageNum, int pageSize, String orderBy, String sortDir);

}
