package com.cursos.app.util;


import com.cursos.app.rest.response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface IGenericCrud<T> {

    T guardar(T t);

    T actualizar(T t);

    T buscarPorId (int id);

    List<T> obtener(int pageNum, int pageSize);

    void eliminar(int id);

}
