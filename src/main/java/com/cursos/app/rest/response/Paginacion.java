package com.cursos.app.rest.response;

import lombok.Data;

import java.util.List;

@Data
public class Paginacion<T> {

    private int pageNumber;
    private int pageSize;
    private List<T> classBody;
    private long totalElements;
    private int totalPages;
    private boolean lastRow;
}
