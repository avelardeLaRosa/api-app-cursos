package com.cursos.app.controller;

import com.cursos.app.dto.DistritoDTO;
import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.expeciones.Messages;
import com.cursos.app.rest.response.ApiResponse;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IDistritoService;
import com.cursos.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("distritos")
@CrossOrigin("http://localhost:4200/")
public class DistritoRestController {

    private final IDistritoService distritoService;

    @Autowired
    public DistritoRestController(IDistritoService distritoService) {
        this.distritoService = distritoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<DistritoDTO>> listarDistritos(
            @RequestParam(value = "pageNum", defaultValue = Constantes.PAGE_NUMBER,required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = Constantes.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = Constantes.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = Constantes.DEFAULT_SORT_BY ,required = false) String sortDir
    ){
        ApiResponse response = new ApiResponse();
        Paginacion usuarios = distritoService.obtenerDistritos(pageNum, pageSize, orderBy, sortDir);
        response.success(Messages.OK.getCode(),Messages.OK.getMessage(),usuarios);
        return new ResponseEntity<>(response,response.getCode());
    }
}
