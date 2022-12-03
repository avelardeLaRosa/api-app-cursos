package com.cursos.app.service.impl;

import com.cursos.app.dto.RolDTO;
import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.entities.RolEntity;
import com.cursos.app.entities.UsuarioEntity;
import com.cursos.app.repository.IRolRepository;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IRolService;
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
public class RolServiceImpl implements IRolService {

    private final IRolRepository rolRepository;

    @Autowired
    public RolServiceImpl(IRolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public RolDTO guardar(RolDTO rolDTO) {
        return null;
    }

    @Override
    public RolDTO actualizar(RolDTO rolDTO) {
        return null;
    }

    @Override
    public RolDTO buscarPorId(int id) {
        Optional<RolEntity> optional = rolRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        RolEntity rol = optional.get();
        RolDTO rolDTO = mapDTO(rol);
        return rolDTO;
    }

    @Override
    public List<RolDTO> obtener(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<RolEntity> roles = rolRepository.findAll(pageable);
        List<RolEntity> listaRoles = roles.getContent();
        return listaRoles.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public Paginacion obtenerRoles(int pageNum, int pageSize, String orderBy, String sortDir) {
        Sort sort = ordenarPor(orderBy,sortDir);
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<RolEntity> roles = rolRepository.findAll(pageable);
        List<RolEntity> listaRoles = roles.getContent();
        List<RolDTO> contenido = listaRoles.stream().map(
                paginacion -> mapDTO(paginacion)).collect(Collectors.toList());

        Paginacion paginacion =  new Paginacion();
        paginacion.setPageNumber(roles.getNumber());
        paginacion.setPageSize(roles.getSize());
        paginacion.setData(contenido);
        paginacion.setTotalElements(roles.getTotalElements());
        paginacion.setTotalPages(roles.getTotalPages());
        paginacion.setLastRow(roles.isLast());
        return paginacion;
    }

    @Override
    public void eliminar(int id) {

    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }

    private RolDTO mapDTO(RolEntity r){
        RolDTO rolDTO = ObjectMapperUtils.map(r, RolDTO.class);
        return rolDTO;
    }
    private RolEntity mapClass(RolDTO d){
        RolEntity rol = ObjectMapperUtils.map(d, RolEntity.class);
        return rol;
    }


}
