package com.cursos.app.service.impl;

import com.cursos.app.dto.DetalleMatriculaDTO;
import com.cursos.app.dto.DetalleMatriculaResponse;
import com.cursos.app.dto.MatriculaDTO;
import com.cursos.app.dto.MatriculaResponse;
import com.cursos.app.entities.CursoEntity;
import com.cursos.app.entities.DetalleMatriculaEntity;
import com.cursos.app.entities.MatriculaEntity;
import com.cursos.app.entities.UsuarioEntity;
import com.cursos.app.expeciones.Messages;
import com.cursos.app.repository.ICursoRepository;
import com.cursos.app.repository.IDetalleMatriculaRepository;
import com.cursos.app.repository.IMatriculaRepository;
import com.cursos.app.repository.IUsuarioRepository;
import com.cursos.app.rest.response.ApiResponse;
import com.cursos.app.service.IMatriculaService;
import com.cursos.app.util.AdminTimeZone;
import com.cursos.app.util.CodeProvider;
import com.cursos.app.util.Constantes;
import com.cursos.app.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

    private final IMatriculaRepository matriculaRepository;
    private final IDetalleMatriculaRepository detalleMatriculaRepository;

    private final IUsuarioRepository usuarioRepository;

    private final ICursoRepository cursoRepository;

    private ApiResponse response = new ApiResponse();

    @Autowired
    public MatriculaServiceImpl(IMatriculaRepository matriculaRepository, IDetalleMatriculaRepository detalleMatriculaRepository, IUsuarioRepository usuarioRepository, ICursoRepository cursoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.detalleMatriculaRepository = detalleMatriculaRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }


    @Override
    public MatriculaResponse guardar(MatriculaDTO matriculaDTO) {

        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findByIdAndStatus(matriculaDTO.getUsuario(), Constantes.CREATED_STATUS);

        MatriculaEntity matricula = new MatriculaEntity();
        matricula.setCode(CodeProvider.generateCode(Constantes.DETALLE_MATRICULA,detalleMatriculaRepository.count()+1,Constantes.LENGTH_CODE));
        matricula.setUsuario(optionalUsuario.get());
        matricula.setStatus(Constantes.CREATED_STATUS);
        matricula.setCreateDate(Date.getCurrent(AdminTimeZone.TIME_ZONE_DEFAULT));
        matricula.setDetalles(null);

        MatriculaEntity matricula1 = matriculaRepository.save(matricula);


        Optional<CursoEntity> optionalCurso = null;
        Optional<MatriculaEntity> optionalMatricula = matriculaRepository.findById(matricula1.getId());

        for (DetalleMatriculaDTO detalleMatriculaDTO : matriculaDTO.getDetalles()){

            optionalCurso = cursoRepository.findById(detalleMatriculaDTO.getCurso());

        }


        CursoEntity curso = optionalCurso.get();

        List<DetalleMatriculaEntity> detallesMatriculas = matriculaDTO.getDetalles().stream().map(
                d -> {
                    DetalleMatriculaEntity m = new DetalleMatriculaEntity();
                    m.setId(d.getId());
                    m.setCreateDate(Date.getCurrent(AdminTimeZone.TIME_ZONE_DEFAULT));
                    m.setStatus(Constantes.CREATED_STATUS);
                    m.setCantidad(d.getCantidad());
                    m.setTotal(d.getTotal());
                    m.setCurso(curso);
                    m.setMatricula(optionalMatricula.get());
                    return m;
                }
        ).collect(Collectors.toList());

        List<DetalleMatriculaEntity> detalles = detalleMatriculaRepository.saveAll(detallesMatriculas);

        List<DetalleMatriculaResponse> detalleMatriculaDTOS = detalles.stream().map(
                dto -> {
                    DetalleMatriculaResponse detalleMatriculaDTO = new DetalleMatriculaResponse();
                    detalleMatriculaDTO.setId(dto.getId());
                    detalleMatriculaDTO.setCantidad(dto.getCantidad());
                    detalleMatriculaDTO.setTotal(dto.getTotal());
                    detalleMatriculaDTO.setCurso(dto.getCurso().getDescripcion());
                    return detalleMatriculaDTO;
                }
        ).collect(Collectors.toList());

        MatriculaResponse matriculaDTO1 = new MatriculaResponse();
        matriculaDTO1.setId(matricula1.getId());
        matriculaDTO1.setCode(matriculaDTO1.getCode());
        matriculaDTO1.setUsuario(matricula1.getUsuario().getNombres()+" "+matricula1.getUsuario().getApellidos());
        matriculaDTO1.setDetalles(detalleMatriculaDTOS);
        return matriculaDTO1;
    }

    @Override
    public MatriculaResponse actualizar(MatriculaDTO matriculaDTO) {

       return null;
    }

    @Override
    public MatriculaResponse buscarPorId(int id) {
        return null;
    }

    @Override
    public MatriculaResponse eliminar(int id) {
        return null;
    }
}
