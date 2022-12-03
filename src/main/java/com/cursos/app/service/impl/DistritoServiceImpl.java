package com.cursos.app.service.impl;

import com.cursos.app.dto.DistritoDTO;
import com.cursos.app.dto.DistritoResponse;
import com.cursos.app.entities.DistritoEntity;
import com.cursos.app.entities.ProvinciaEntity;
import com.cursos.app.repository.IDistritoRepository;
import com.cursos.app.repository.IProvinciaRepository;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IDistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistritoServiceImpl implements IDistritoService {

    private final IDistritoRepository distritoRepository;
    private final IProvinciaRepository provinciaRepository;

    @Autowired
    public DistritoServiceImpl(IDistritoRepository distritoRepository, IProvinciaRepository provinciaRepository) {
        this.distritoRepository = distritoRepository;
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public DistritoDTO guardar(DistritoDTO distritoDTO) {

        return null;
    }


    @Override
    public DistritoDTO guardar(DistritoResponse distritoResponse) {

        Optional<DistritoEntity> distrito = distritoRepository.findByDescripcion(distritoResponse.getDescripcion());
        if(distrito.isPresent()){
            return null;
        }
        Optional<ProvinciaEntity> optionalProvincia = provinciaRepository.findById(distritoResponse.getProvincia());
        DistritoEntity distrito1 = new DistritoEntity();
        distrito1.setDescripcion(distritoResponse.getDescripcion());
        distrito1.setProvincia(optionalProvincia.get());

        DistritoEntity distrito2 = distritoRepository.save(distrito1);

        DistritoDTO distritoDTO = new DistritoDTO();
        distritoDTO.setId(distrito2.getId());
        distritoDTO.setDescripcion(distrito2.getDescripcion());
        distritoDTO.setProvincia(distrito2.getProvincia().getDescripcion());
        return distritoDTO;
    }
    @Override
    public DistritoDTO actualizar(DistritoDTO distritoDTO) {
        return null;
    }

    @Override
    public DistritoDTO buscarPorId(int id) {
        return null;
    }

    @Override
    public DistritoResponse getById(int id){
        Optional<DistritoEntity> optional = distritoRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        DistritoEntity distrito = optional.get();
        DistritoResponse distritoDTO = new DistritoResponse();
        distritoDTO.setId(distritoDTO.getId());
        distritoDTO.setDescripcion(distritoDTO.getDescripcion());
        distritoDTO.setProvincia(distrito.getProvincia().getId());
        return distritoDTO;
    }

    @Override
    public List<DistritoDTO> obtener(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Paginacion obtenerDistritos(int pageNum, int pageSize, String orderBy, String sortDir) {
        Sort sort = ordenarPor(orderBy,sortDir);
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<DistritoEntity> distritos = distritoRepository.findAll(pageable);
        List<DistritoEntity> listaDistritos = distritos.getContent();
        List<DistritoDTO> contenido = listaDistritos.stream().map(
                paginacion -> {
                    DistritoDTO dto = new DistritoDTO();
                    dto.setId(paginacion.getId());
                    dto.setDescripcion(paginacion.getDescripcion());
                    dto.setProvincia(paginacion.getProvincia().getDescripcion());
                    return dto;
                }).collect(Collectors.toList());

        Paginacion paginacion =  new Paginacion();
        paginacion.setPageNumber(distritos.getNumber());
        paginacion.setPageSize(distritos.getSize());
        paginacion.setData(contenido);
        paginacion.setTotalElements(distritos.getTotalElements());
        paginacion.setTotalPages(distritos.getTotalPages());
        paginacion.setLastRow(distritos.isLast());
        return paginacion;
    }

    @Override
    public void eliminar(int id) {


    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }




}
