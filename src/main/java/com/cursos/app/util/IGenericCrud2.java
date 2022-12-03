package com.cursos.app.util;

import com.cursos.app.rest.response.ApiResponse;

import java.util.List;

public interface IGenericCrud2<T,D> {

    ApiResponse<T> guardar(D d);

    ApiResponse<T> actualizar(D d);

    ApiResponse<T> buscarPorId (int id);

    ApiResponse<T> eliminar(int id);

}
