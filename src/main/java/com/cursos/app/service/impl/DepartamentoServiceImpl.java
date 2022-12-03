package com.cursos.app.service.impl;

import com.cursos.app.dto.DepartamentoDTO;
import com.cursos.app.dto.ProvinciaDTO;
import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.entities.DepartamentoEntity;
import com.cursos.app.entities.UsuarioEntity;
import com.cursos.app.repository.IDepartamentoRepository;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IDepartamentoService;
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
public class DepartamentoServiceImpl implements IDepartamentoService {

    private final IDepartamentoRepository departamentoRepository;

    @Autowired
    public DepartamentoServiceImpl(IDepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public DepartamentoDTO guardar(DepartamentoDTO departamentoDTO) {
        return null;
    }

    @Override
    public DepartamentoDTO actualizar(DepartamentoDTO departamentoDTO) {
        return null;
    }

    @Override
    public DepartamentoDTO buscarPorId(int id) {
        Optional<DepartamentoEntity> optional = departamentoRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        DepartamentoEntity departamento = optional.get();
        DepartamentoDTO departamentoDTO = mapDTO(departamento);
        return departamentoDTO;
    }

    @Override
    public List<DepartamentoDTO> obtener(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<DepartamentoEntity> departamentos = departamentoRepository.findAll(pageable);
        List<DepartamentoEntity> listaDepartamentos = departamentos.getContent();
        return listaDepartamentos.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public Paginacion obtenerDepartamentos(int pageNum, int pageSize, String orderBy, String sortDir) {
        Sort sort = ordenarPor(orderBy,sortDir);
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<DepartamentoEntity> departamentos = departamentoRepository.findAll(pageable);
        List<DepartamentoEntity> listaDepartamentos = departamentos.getContent();
        List<DepartamentoDTO> contenido = listaDepartamentos.stream().map(
                paginacion -> mapDTO(paginacion)).collect(Collectors.toList());

        Paginacion paginacion =  new Paginacion();
        paginacion.setPageNumber(departamentos.getNumber());
        paginacion.setPageSize(departamentos.getSize());
        paginacion.setData(contenido);
        paginacion.setTotalElements(departamentos.getTotalElements());
        paginacion.setTotalPages(departamentos.getTotalPages());
        paginacion.setLastRow(departamentos.isLast());
        return paginacion;
    }

    @Override
    public void eliminar(int id) {

    }

    private DepartamentoDTO mapDTO(DepartamentoEntity d){
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setId(d.getId());
        departamentoDTO.setDescripcion(d.getDescripcion());
       /* List<ProvinciaDTO> provincias = d.getProvincias().stream().map(
                p -> {
                    ProvinciaDTO provinciaDTO = new ProvinciaDTO();
                    provinciaDTO.setId(p.getId());
                    provinciaDTO.setDescripcion(p.getDescripcion());
                    return provinciaDTO;
                }
        ).collect(Collectors.toList());
        departamentoDTO.setProvincia(provincias);*/
        return departamentoDTO;
    }



    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }


}
