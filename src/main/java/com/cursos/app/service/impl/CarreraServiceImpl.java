package com.cursos.app.service.impl;

import com.cursos.app.dto.CarreraDTO;
import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.entities.CarreraEntity;
import com.cursos.app.entities.UsuarioEntity;
import com.cursos.app.repository.ICarreraRepository;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.ICarreraService;
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
public class CarreraServiceImpl implements ICarreraService {

    private final ICarreraRepository carreraRepository;

    @Autowired
    public CarreraServiceImpl(ICarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Override
    public CarreraDTO guardar(CarreraDTO carreraDTO) {
        return null;
    }

    @Override
    public CarreraDTO actualizar(CarreraDTO carreraDTO) {
        return null;
    }

    @Override
    public CarreraDTO buscarPorId(int id) {
        Optional<CarreraEntity> optional = carreraRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        CarreraEntity carrera = optional.get();
        CarreraDTO carreraDTO = mapDTO(carrera);
        return carreraDTO;
    }

    @Override
    public List<CarreraDTO> obtener(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CarreraEntity> carreras = carreraRepository.findAll(pageable);
        List<CarreraEntity> listaCarreras = carreras.getContent();
        return listaCarreras.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public Paginacion obtenerCarreras(Map<String,Object> parameters) {
        Sort sort = ordenarPor(parameters.get(Constantes.PARAM_ORDER_BY).toString(),parameters.get(Constantes.PARAM_SORT_BY).toString());
        Pageable pageable = PageRequest.of(Integer.parseInt(parameters.get(Constantes.PARAM_PAGE_NUMBER).toString()),Integer.parseInt(parameters.get(Constantes.PARAM_PAGE_SIZE).toString()),sort);
        Page<CarreraEntity> carreras = carreraRepository.findAll(pageable);
        List<CarreraEntity> listaCarreras = carreras.getContent();
        List<CarreraDTO> contenido = listaCarreras.stream().map(
                paginacion -> mapDTO(paginacion)).collect(Collectors.toList());

        Paginacion paginacion =  new Paginacion();
        paginacion.setPageNumber(carreras.getNumber());
        paginacion.setPageSize(carreras.getSize());
        paginacion.setData(contenido);
        paginacion.setTotalElements(carreras.getTotalElements());
        paginacion.setTotalPages(carreras.getTotalPages());
        paginacion.setLastRow(carreras.isLast());
        return paginacion;
    }

    @Override
    public void eliminar(int id) {

    }

    private CarreraDTO mapDTO(CarreraEntity c){
        CarreraDTO carreraDTO = ObjectMapperUtils.map(c, CarreraDTO.class);
        return carreraDTO;
    }
    private CarreraEntity mapClass(CarreraDTO d){
        CarreraEntity carrera = ObjectMapperUtils.map(d, CarreraEntity.class);
        return carrera;
    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }

}
