package com.cursos.app.service;


import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.rest.request.UsuarioRequest;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.util.IGenericCrud;

public interface IUsuarioService extends IGenericCrud<UsuarioDTO> {

    public Paginacion obtenerUsuarios(int pageNum, int pageSize, String orderBy, String sortDir);

    public UsuarioDTO agregar(UsuarioRequest request);
    public UsuarioDTO editar(UsuarioRequest request);
}
