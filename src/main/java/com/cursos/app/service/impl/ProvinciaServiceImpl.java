package com.cursos.app.service.impl;

import com.cursos.app.dto.DepartamentoDTO;
import com.cursos.app.dto.DistritoDTO;
import com.cursos.app.dto.ProvinciaDTO;
import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.entities.DepartamentoEntity;
import com.cursos.app.entities.ProvinciaEntity;
import com.cursos.app.entities.UsuarioEntity;
import com.cursos.app.repository.IProvinciaRepository;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IProvinciaService;
import com.cursos.app.util.Constantes;
import com.cursos.app.util.ObjectMapperUtils;
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
public class ProvinciaServiceImpl implements IProvinciaService {

    private final IProvinciaRepository provinciaRepository;



    @Autowired
    public ProvinciaServiceImpl(IProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public ProvinciaDTO guardar(ProvinciaDTO provinciaDTO) {
        return null;
    }

    @Override
    public ProvinciaDTO actualizar(ProvinciaDTO provinciaDTO) {
        return null;
    }

    @Override
    public ProvinciaDTO buscarPorId(int id) {
        Optional<ProvinciaEntity> optional = provinciaRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        ProvinciaEntity provincia = optional.get();

        ProvinciaDTO provinciaDTO = new ProvinciaDTO();
        provinciaDTO.setId(provincia.getId());
        provinciaDTO.setDescripcion(provincia.getDescripcion());
        provinciaDTO.setDepartamento(provincia.getDepartamento().getDescripcion());
        //List<DistritoDTO> distritoDTOS = ObjectMapperUtils.mapAll(provincia.getDistritos(), DistritoDTO.class);
        //provinciaDTO.setDistritos(distritoDTOS);
        return provinciaDTO;
    }

    @Override
    public List<ProvinciaDTO> obtener(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ProvinciaEntity> provincias = provinciaRepository.findAll(pageable);
        List<ProvinciaEntity> lista = provincias.getContent();
        return lista.stream().map(
                p -> {
                    ProvinciaDTO provinciaDTO = new ProvinciaDTO();
                    provinciaDTO.setId(p.getId());
                    provinciaDTO.setDescripcion(p.getDescripcion());
                    return provinciaDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public Paginacion obtenerProvincias(Map<String,Object> parameters) {
        Sort sort = ordenarPor(parameters.get(Constantes.PARAM_ORDER_BY).toString(),parameters.get(Constantes.PARAM_SORT_BY).toString());
        Pageable pageable = PageRequest.of(Integer.parseInt(parameters.get(Constantes.PARAM_PAGE_NUMBER).toString()),Integer.parseInt(parameters.get(Constantes.PARAM_PAGE_SIZE).toString()),sort);
        Page<ProvinciaEntity> provincias = provinciaRepository.findAll(pageable);
        List<ProvinciaEntity> listaProvincias = provincias.getContent();
        List<ProvinciaDTO> contenido = listaProvincias.stream().map(
                paginacion -> {
                    ProvinciaDTO res = new ProvinciaDTO();
                    res.setId(paginacion.getId());
                    res.setDescripcion(paginacion.getDescripcion());
                    return res;
                }).collect(Collectors.toList());

        Paginacion paginacion =  new Paginacion();
        paginacion.setPageNumber(provincias.getNumber());
        paginacion.setPageSize(provincias.getSize());
        paginacion.setData(contenido);
        paginacion.setTotalElements(provincias.getTotalElements());
        paginacion.setTotalPages(provincias.getTotalPages());
        paginacion.setLastRow(provincias.isLast());
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
