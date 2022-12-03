package com.cursos.app.controller;

import com.cursos.app.dto.CursoDTO;
import com.cursos.app.dto.MatriculaDTO;
import com.cursos.app.dto.MatriculaResponse;
import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.expeciones.Messages;
import com.cursos.app.rest.response.ApiResponse;
import com.cursos.app.service.ICursoService;
import com.cursos.app.service.IMatriculaService;
import com.cursos.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("matricula")
@CrossOrigin("http://localhost:4200/")
public class MatriculaRestController {

    private final IMatriculaService matriculaService;

    private final IUsuarioService usuarioService;
    private final ICursoService cursoService;

    @Autowired
    public MatriculaRestController(IMatriculaService matriculaService, IUsuarioService usuarioService, ICursoService cursoService) {
        this.matriculaService = matriculaService;
        this.usuarioService = usuarioService;
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MatriculaResponse>> guardar(
            @RequestBody MatriculaDTO matriculaDTO
            ){

        ApiResponse response = new ApiResponse();

        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(matriculaDTO.getUsuario());
        if(usuarioDTO==null){
            response.failed(Messages.USUARIO_NOT_FOUND.getCode(), Messages.USUARIO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }



        CursoDTO cursoDTO = cursoService.buscarPorId(matriculaDTO.getDetalles().get(matriculaDTO.getDetalles().size()-1).getCurso());
        if(cursoDTO==null){
            response.failed(Messages.CURSO_NOT_FOUND.getCode(), Messages.CURSO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        MatriculaResponse matriculaResponse = matriculaService.guardar(matriculaDTO);
        response.success(Messages.CREATED.getCode(), Messages.CREATED.getMessage(),matriculaResponse);
        return new ResponseEntity<>(response,response.getCode());
    }
}
