package com.cursos.app.util;

import com.cursos.app.rest.response.ApiResponse;

import java.util.List;

public interface IGenericCrud2<T,D> {

    T guardar(D d);

    T actualizar(D d);

    T buscarPorId (int id);

    T eliminar(int id);

}
