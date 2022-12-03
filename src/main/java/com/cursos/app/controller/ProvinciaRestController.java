package com.cursos.app.controller;

import com.cursos.app.dto.ProvinciaDTO;
import com.cursos.app.expeciones.Messages;
import com.cursos.app.rest.response.ApiResponse;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IProvinciaService;
import com.cursos.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("provincias")
@CrossOrigin("http://localhost:4200/")
public class ProvinciaRestController {

    private final IProvinciaService provinciaService;

    @Autowired
    public ProvinciaRestController(IProvinciaService provinciaService) {
        this.provinciaService = provinciaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ProvinciaDTO>> getAutomoviles(
            @RequestParam(value = "pageNum", defaultValue = Constantes.PAGE_NUMBER,required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = Constantes.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "orderBy",defaultValue = Constantes.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortBy",defaultValue = Constantes.DEFAULT_SORT_BY ,required = false) String sortDir
    ){
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("pageNum",pageNum);
        parameters.put("pageSize",pageSize);
        parameters.put("orderBy",orderBy);
        parameters.put("sortBy",sortDir);
        ApiResponse response = new ApiResponse();
        Paginacion provincias = provinciaService.obtenerProvincias(parameters);
        response.success(Messages.OK.getCode(), Messages.OK.getMessage(),provincias);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
