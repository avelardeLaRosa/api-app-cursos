package com.cursos.app.service.impl;

import com.cursos.app.dto.DepartamentoDTO;
import com.cursos.app.dto.DistritoDTO;
import com.cursos.app.dto.ProvinciaDTO;
import com.cursos.app.entities.DepartamentoEntity;
import com.cursos.app.entities.ProvinciaEntity;
import com.cursos.app.repository.IProvinciaRepository;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IProvinciaService;
import com.cursos.app.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<DistritoDTO> distritoDTOS = ObjectMapperUtils.mapAll(provincia.getDistritos(), DistritoDTO.class);
        provinciaDTO.setDistritos(distritoDTOS);
        return provinciaDTO;
    }

    @Override
    public List<ProvinciaDTO> obtener(int pageNum, int pageSize) {
        return null;
    }


    @Override
    public Paginacion obtenerProvincias(int pageNum, int pageSize, String orderBy, String sortDir) {
        return null;
    }

    @Override
    public void eliminar(int id) {

    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }


}
