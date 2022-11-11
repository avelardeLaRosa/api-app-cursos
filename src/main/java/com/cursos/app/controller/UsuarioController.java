package com.cursos.app.controller;

import com.cursos.app.dto.*;
import com.cursos.app.expeciones.Messages;
import com.cursos.app.rest.request.UsuarioRequest;
import com.cursos.app.rest.response.ApiResponse;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IDistritoService;
import com.cursos.app.service.IRolService;
import com.cursos.app.service.IUsuarioService;
import com.cursos.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final IUsuarioService usuarioService;
    private final IDistritoService distritoService;

    private final IRolService rolService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService, IDistritoService distritoService, IRolService rolService) {
        this.usuarioService = usuarioService;
        this.distritoService = distritoService;
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<UsuarioDTO>> listarUsuarios(
            @RequestParam(value = "pageNum", defaultValue = Constantes.PAGE_NUMBER,required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = Constantes.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = Constantes.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = Constantes.DEFAULT_SORT_BY ,required = false) String sortDir
    ){
        ApiResponse response = new ApiResponse();
        Paginacion usuarios = usuarioService.obtenerUsuarios(pageNum, pageSize, orderBy, sortDir);
        response.success(Messages.OK.getCode(),Messages.OK.getMessage(),usuarios);
        return new ResponseEntity<>(response,response.getCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> buscarPorId(
            @PathVariable("id") int id
    ){
        ApiResponse<UsuarioDTO> response = new ApiResponse<>();
        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(id);
        if(usuarioDTO==null){
            response.failed(Messages.USER_NOT_FOUND.getCode(), Messages.USER_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.OK.getCode(), Messages.OK.getMessage(),usuarioDTO);
        return new ResponseEntity<>(response,response.getCode());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioDTO>> agregar(
            @RequestBody UsuarioRequest request
    ){
        ApiResponse<UsuarioDTO> response = new ApiResponse<>();

        DistritoDTO distritoDTO = distritoService.buscarPorId(request.getDistrito());
        if(distritoDTO==null){
            response.failed(Messages.DISTRICT_NOT_FOUND.getCode(), Messages.DISTRICT_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        RolDTO rolDTO = rolService.buscarPorId(request.getRol());
        if(rolDTO==null){
            response.failed(Messages.ROLE_NOT_FOUND.getCode(), Messages.ROLE_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        UsuarioDTO uDto = usuarioService.agregar(request);
        if(uDto==null){
            response.failed(Messages.EMAIL_EXISTS.getCode(), Messages.EMAIL_EXISTS.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.CREATED.getCode(), Messages.CREATED.getMessage(),uDto);
        return new ResponseEntity<>(response,response.getCode());

    }

    @PatchMapping
    public ResponseEntity<ApiResponse<UsuarioDTO>> actualizar(
            @RequestBody UsuarioRequest usuarioRequest
    ){
        ApiResponse<UsuarioDTO> response = new ApiResponse();

        DistritoDTO distritoDTO = distritoService.buscarPorId(usuarioRequest.getDistrito());
        if(distritoDTO==null){
            response.failed(Messages.DISTRICT_NOT_FOUND.getCode(), Messages.DISTRICT_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        RolDTO rolDTO = rolService.buscarPorId(usuarioRequest.getRol());
        if(rolDTO==null){
            response.failed(Messages.ROLE_NOT_FOUND.getCode(), Messages.ROLE_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        UsuarioDTO uDto = usuarioService.editar(usuarioRequest);
        if(uDto==null){
            response.failed(Messages.USER_NOT_FOUND.getCode(), Messages.USER_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.UPDATED.getCode(), Messages.UPDATED.getMessage(),uDto);
        return new ResponseEntity<>(response,response.getCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> eliminar(
            @PathVariable("id") Integer id
    ){
        ApiResponse<UsuarioDTO> response = new ApiResponse<>();
        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(id);
        if(usuarioDTO==null){
            response.failed(Messages.USER_NOT_FOUND.getCode(), Messages.USER_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        usuarioService.eliminar(usuarioDTO.getId());
        response.success(Messages.DELETED.getCode(), Messages.DELETED.getMessage());
        return new ResponseEntity<>(response,response.getCode());

    }


    


}
