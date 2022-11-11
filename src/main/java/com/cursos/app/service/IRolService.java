package com.cursos.app.service;

import com.cursos.app.dto.RolDTO;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.util.IGenericCrud;

public interface IRolService extends IGenericCrud<RolDTO> {

    public Paginacion obtenerRoles(int pageNum, int pageSize, String orderBy, String sortDir);

}
