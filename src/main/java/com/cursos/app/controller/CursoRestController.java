package com.cursos.app.controller;

import com.cursos.app.dto.CarreraDTO;
import com.cursos.app.dto.CursoDTO;
import com.cursos.app.dto.CursoRequest;
import com.cursos.app.dto.ProvinciaDTO;
import com.cursos.app.expeciones.Messages;
import com.cursos.app.rest.response.ApiResponse;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.ICarreraService;
import com.cursos.app.service.ICursoService;
import com.cursos.app.service.IUsuarioService;
import com.cursos.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("cursos")
@CrossOrigin("http://localhost:4200/")
public class CursoRestController {

    private final ICursoService cursoService;

    private final ICarreraService carreraService;

    private final IUsuarioService usuarioService;

    @Autowired
    public CursoRestController(ICursoService cursoService, ICarreraService carreraService, IUsuarioService usuarioService) {
        this.cursoService = cursoService;
        this.carreraService = carreraService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ProvinciaDTO>> getProvincias(
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
        Paginacion cursos = cursoService.obtenerCursos(parameters);
        response.success(Messages.OK.getCode(), Messages.OK.getMessage(),cursos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CursoDTO>> buscarPorId(
            @PathVariable(value = "id") int id
    ){
        ApiResponse response = new ApiResponse();

        CursoDTO cursoDTO = cursoService.buscarPorId(id);
        if(cursoDTO==null){
            response.failed(Messages.CURSO_EXISTS.getCode(), Messages.CURSO_EXISTS.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.CREATED.getCode(),Messages.CREATED.getMessage(),cursoDTO);
        return new ResponseEntity<>(response, response.getCode());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CursoDTO>> guardar(
            @RequestBody CursoRequest cursoRequest
            ){
        ApiResponse response = new ApiResponse();

        CarreraDTO carreraDTO = carreraService.buscarPorId(cursoRequest.getCarrera());
        if(carreraDTO==null){
            response.failed(Messages.CARRERA_NOT_FOUND.getCode(), Messages.CARRERA_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        CursoDTO cursoDTO = cursoService.guardar(cursoRequest);
        if(cursoDTO==null){
            response.failed(Messages.CURSO_EXISTS.getCode(), Messages.CURSO_EXISTS.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.CREATED.getCode(),Messages.CREATED.getMessage(),cursoDTO);
        return new ResponseEntity<>(response, response.getCode());
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CursoDTO>> actualizar(
            @RequestBody CursoRequest cursoRequest
    ){
        ApiResponse response = new ApiResponse();

        CarreraDTO carreraDTO = carreraService.buscarPorId(cursoRequest.getCarrera());
        if(carreraDTO==null){
            response.failed(Messages.CARRERA_NOT_FOUND.getCode(), Messages.CARRERA_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        CursoDTO cursoDTO = cursoService.actualizar(cursoRequest);
        if(cursoDTO==null){
            response.failed(Messages.CURSO_NOT_FOUND.getCode(), Messages.CURSO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.UPDATED.getCode(),Messages.UPDATED.getMessage(),cursoDTO);
        return new ResponseEntity<>(response, response.getCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CursoDTO>> eliminar(
            @PathVariable(value = "id") int id
    ){
        ApiResponse response = new ApiResponse();
        CursoDTO cursoDTO = cursoService.eliminar(id);
        if(cursoDTO==null){
            response.failed(Messages.CURSO_NOT_FOUND.getCode(), Messages.CURSO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.UPDATED.getCode(),Messages.UPDATED.getMessage(),cursoDTO);
        return new ResponseEntity<>(response, response.getCode());
    }




}
