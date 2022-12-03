package com.cursos.app.service.impl;

import com.cursos.app.dto.CursoDTO;
import com.cursos.app.dto.CursoRequest;
import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.entities.CarreraEntity;
import com.cursos.app.entities.CursoEntity;
import com.cursos.app.entities.ProvinciaEntity;
import com.cursos.app.entities.UsuarioEntity;
import com.cursos.app.expeciones.Messages;
import com.cursos.app.repository.ICarreraRepository;
import com.cursos.app.repository.ICursoRepository;
import com.cursos.app.rest.request.UsuarioRequest;
import com.cursos.app.rest.response.ApiResponse;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.ICursoService;
import com.cursos.app.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements ICursoService {

    private final ICursoRepository cursoRepository;
    private final ICarreraRepository carreraRepository;

    private ApiResponse response = new ApiResponse();


    @Autowired
    public CursoServiceImpl(ICursoRepository cursoRepository, ICarreraRepository carreraRepository) {
        this.cursoRepository = cursoRepository;
        this.carreraRepository = carreraRepository;
    }

    @Override
    public ApiResponse<CursoDTO> guardar(CursoRequest cursoRequest) {

        Optional<CursoEntity> optionalCurso = cursoRepository.findByDescripcion(cursoRequest.getDescripcion());
        if(optionalCurso.isPresent()){
            return response.failed(Messages.CURSO_EXISTS.getCode(),Messages.CURSO_EXISTS.getMessage());
        }

        Optional<CarreraEntity> optionalCarrera = carreraRepository.findById(cursoRequest.getCarrera());
        if(optionalCarrera.isEmpty()){
            return response.failed(Messages.CARRERA_NOT_FOUND.getCode(),Messages.CARRERA_NOT_FOUND.getMessage());
        }

        CursoEntity curso = new CursoEntity();
        curso.setCode(CodeProvider.generateCode(Constantes.CURSO,cursoRepository.count()+1,Constantes.LENGTH_CODE));
        curso.setStatus(Constantes.CREATED_STATUS);
        curso.setDescripcion(cursoRequest.getDescripcion());
        curso.setCreditos(cursoRequest.getCreditos());
        curso.setPrecio(cursoRequest.getPrecio());
        curso.setCarrera(optionalCarrera.get());
        curso.setCreateDate(Date.getCurrent(AdminTimeZone.TIME_ZONE_DEFAULT));


        CursoEntity nuevoCurso = cursoRepository.save(curso);

        CursoDTO dto = new CursoDTO();
        dto.setId(nuevoCurso.getId());
        dto.setCode(nuevoCurso.getCode());
        dto.setDescripcion(nuevoCurso.getDescripcion());
        dto.setCreditos(nuevoCurso.getCreditos());
        dto.setPrecio(nuevoCurso.getPrecio());
        dto.setCarrera(nuevoCurso.getCarrera().getDescripcion());

        return response.success(Messages.CREATED.getCode(),Messages.CREATED.getMessage(),dto);

    }

    @Override
    public ApiResponse<CursoDTO> actualizar(CursoRequest cursoRequest) {

        Optional<CursoEntity> optionalCurso = cursoRepository.findById(cursoRequest.getId());
        if(optionalCurso.isEmpty()){
            return response.failed(Messages.CURSO_NOT_FOUND.getCode(),Messages.CURSO_NOT_FOUND.getMessage());
        }

        Optional<CarreraEntity> optionalCarrera = carreraRepository.findById(cursoRequest.getCarrera());
        if(optionalCarrera.isEmpty()){
            return response.failed(Messages.CARRERA_NOT_FOUND.getCode(),Messages.CARRERA_NOT_FOUND.getMessage());
        }

        CursoEntity curso = optionalCurso.get();
        curso.setDescripcion(cursoRequest.getDescripcion());
        curso.setCreditos(cursoRequest.getCreditos());
        curso.setPrecio(cursoRequest.getPrecio());
        curso.setCarrera(optionalCarrera.get());
        curso.setUpdateDate(Date.getCurrent(AdminTimeZone.TIME_ZONE_DEFAULT));

        CursoEntity nuevoCurso = cursoRepository.save(curso);

        CursoDTO dto = new CursoDTO();
        dto.setId(nuevoCurso.getId());
        dto.setCode(nuevoCurso.getCode());
        dto.setDescripcion(nuevoCurso.getDescripcion());
        dto.setCreditos(nuevoCurso.getCreditos());
        dto.setPrecio(nuevoCurso.getPrecio());
        dto.setCarrera(nuevoCurso.getCarrera().getDescripcion());
        return response.success(Messages.UPDATED.getCode(),Messages.UPDATED.getMessage(),dto);
    }

    @Override
    public ApiResponse<CursoDTO> buscarPorId(int id) {

        Optional<CursoEntity> optional = cursoRepository.findByIdAndStatus(id,Constantes.CREATED_STATUS);
        if(optional.isEmpty()){
            return response.failed(Messages.CURSO_NOT_FOUND.getCode(),Messages.CURSO_NOT_FOUND.getMessage());
        }

        CursoEntity curso = optional.get();
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setCode(curso.getCode());
        dto.setDescripcion(curso.getDescripcion());
        dto.setCreditos(curso.getCreditos());
        dto.setPrecio(curso.getPrecio());
        dto.setCarrera(curso.getCarrera().getDescripcion());
        return response.success(Messages.OK.getCode(),Messages.OK.getMessage(),dto);

    }


    @Override
    public ApiResponse<CursoDTO> eliminar(int id) {

        Optional<CursoEntity> optional = cursoRepository.findById(id);
        if(optional.isEmpty()){
            return response.failed(Messages.CURSO_NOT_FOUND.getCode(),Messages.CURSO_NOT_FOUND.getMessage());
        }

        CursoEntity curso = optional.get();
        curso.setStatus(Constantes.DELETED_STATUS);
        curso.setDeleteDate(Date.getCurrent(AdminTimeZone.TIME_ZONE_DEFAULT));

        CursoEntity c = cursoRepository.save(curso);

        CursoDTO dto = new CursoDTO();
        dto.setId(c.getId());
        dto.setCode(c.getCode());
        dto.setDescripcion(c.getDescripcion());
        dto.setCreditos(c.getCreditos());
        dto.setPrecio(c.getPrecio());
        dto.setCarrera(c.getCarrera().getDescripcion());
        return response.success(Messages.DELETED.getCode(),Messages.DELETED.getMessage(),dto);
    }



    @Override
    public Paginacion obtenerCursos(Map<String, Object> parameters) {
        Sort sort = ordenarPor(parameters.get(Constantes.PARAM_ORDER_BY).toString(),parameters.get(Constantes.PARAM_SORT_BY).toString());
        Pageable pageable = PageRequest.of(Integer.parseInt(parameters.get(Constantes.PARAM_PAGE_NUMBER).toString()),Integer.parseInt(parameters.get(Constantes.PARAM_PAGE_SIZE).toString()),sort);
        Page<CursoEntity> cursos = cursoRepository.getCursoEntities(pageable,Constantes.CREATED_STATUS);
        List<CursoEntity> listaCursos = cursos.getContent();
        List<CursoDTO> contenido = listaCursos.stream().map(
                paginacion ->{

                    CursoDTO dto = new CursoDTO();
                    dto.setId(paginacion.getId());
                    dto.setCode(paginacion.getCode());
                    dto.setDescripcion(paginacion.getDescripcion());
                    dto.setCreditos(paginacion.getCreditos());
                    dto.setPrecio(paginacion.getPrecio());
                    dto.setCarrera(paginacion.getCarrera().getDescripcion());
                    return dto;
                }
        ).collect(Collectors.toList());

        Paginacion paginacion =  new Paginacion();
        paginacion.setPageNumber(cursos.getNumber());
        paginacion.setPageSize(cursos.getSize());
        paginacion.setData(contenido);
        paginacion.setTotalElements(cursos.getTotalElements());
        paginacion.setTotalPages(cursos.getTotalPages());
        paginacion.setLastRow(cursos.isLast());
        return paginacion;
    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }
}
