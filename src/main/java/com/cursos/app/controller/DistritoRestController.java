package com.cursos.app.controller;

import com.cursos.app.dto.*;
import com.cursos.app.expeciones.Messages;
import com.cursos.app.rest.response.ApiResponse;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IDistritoService;
import com.cursos.app.service.IProvinciaService;
import com.cursos.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("distritos")
@CrossOrigin("http://localhost:4200/")
public class DistritoRestController {

    private final IDistritoService distritoService;
    private final IProvinciaService provinciaService;

    @Autowired
    public DistritoRestController(IDistritoService distritoService, IProvinciaService provinciaService) {
        this.distritoService = distritoService;
        this.provinciaService = provinciaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<DistritoDTO>> listarDistritos(
            @RequestParam(value = "pageNum", defaultValue = Constantes.PAGE_NUMBER,required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = Constantes.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = Constantes.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = Constantes.DEFAULT_SORT_BY ,required = false) String sortDir
    ){
        ApiResponse response = new ApiResponse();
        Paginacion distritos = distritoService.obtenerDistritos(pageNum, pageSize, orderBy, sortDir);
        response.success(Messages.OK.getCode(),Messages.OK.getMessage(),distritos);
        return new ResponseEntity<>(response,response.getCode());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DistritoDTO>> guardar(
            @RequestBody DistritoResponse distritoResponse
    ){

        ApiResponse response = new ApiResponse();

        ProvinciaDTO provinciaDTO = provinciaService.buscarPorId(distritoResponse.getProvincia());
        if(provinciaDTO==null){
            response.failed(Messages.PROVINCIA_NOT_FOUND.getCode(), Messages.PROVINCIA_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        DistritoDTO distritoDTO = distritoService.guardar(distritoResponse);
        if(distritoDTO==null){
            response.failed(Messages.DISTRIC_EXISTS.getCode(), Messages.DISTRIC_EXISTS.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.CREATED.getCode(), Messages.CREATED.getMessage(),distritoDTO);
        return new ResponseEntity<>(response,response.getCode());
    }


}
