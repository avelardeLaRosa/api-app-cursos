package com.cursos.app.service;

import com.cursos.app.dto.CarreraDTO;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.util.IGenericCrud;

public interface ICarreraService extends IGenericCrud<CarreraDTO> {

    public Paginacion obtenerCarreras(int pageNum, int pageSize, String orderBy, String sortDir);

}
