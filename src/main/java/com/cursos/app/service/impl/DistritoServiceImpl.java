package com.cursos.app.service.impl;

import com.cursos.app.dto.DistritoDTO;
import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.entities.DistritoEntity;
import com.cursos.app.entities.UsuarioEntity;
import com.cursos.app.repository.IDistritoRepository;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IDistritoService;
import com.cursos.app.util.ObjectMapperUtils;
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

    @Autowired
    public DistritoServiceImpl(IDistritoRepository distritoRepository) {
        this.distritoRepository = distritoRepository;
    }

    @Override
    public DistritoDTO guardar(DistritoDTO distritoDTO) {
        return null;
    }

    @Override
    public DistritoDTO actualizar(DistritoDTO distritoDTO) {
        return null;
    }

    @Override
    public DistritoDTO buscarPorId(int id) {
        Optional<DistritoEntity> optional = distritoRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        DistritoEntity distrito = optional.get();
        DistritoDTO distritoDTO = mapToList(distrito);
        return distritoDTO;
    }

    @Override
    public List<DistritoDTO> obtener(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<DistritoEntity> distritos = distritoRepository.findAll(pageable);
        List<DistritoEntity> listaDistritos = distritos.getContent();
        return listaDistritos.stream().map(this::mapToList).collect(Collectors.toList());
    }

    @Override
    public Paginacion obtenerDistritos(int pageNum, int pageSize, String orderBy, String sortDir) {
        Sort sort = ordenarPor(orderBy,sortDir);
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<DistritoEntity> distritos = distritoRepository.findAll(pageable);
        List<DistritoEntity> listaDistritos = distritos.getContent();
        List<DistritoDTO> contenido = listaDistritos.stream().map(
                paginacion -> mapToList(paginacion)).collect(Collectors.toList());

        Paginacion paginacion =  new Paginacion();
        paginacion.setPageNumber(distritos.getNumber());
        paginacion.setPageSize(distritos.getSize());
        paginacion.setClassBody(contenido);
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

    public DistritoDTO mapToList(DistritoEntity d){
        DistritoDTO distritoDTO = new DistritoDTO();
        distritoDTO.setId(d.getId());
        distritoDTO.setDescripcion(d.getDescripcion());
        distritoDTO.setProvincia(d.getProvincia().getDescripcion());
        List<UsuarioDTO> usuarios = ObjectMapperUtils.mapAll(distritoDTO.getUsuarios(),UsuarioDTO.class);
        distritoDTO.setUsuarios(usuarios);
        return distritoDTO;
    }


}
