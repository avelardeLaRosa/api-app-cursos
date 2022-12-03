package com.cursos.app.service.impl;

import com.cursos.app.dto.UsuarioDTO;
import com.cursos.app.entities.DistritoEntity;
import com.cursos.app.entities.RolEntity;
import com.cursos.app.entities.UsuarioEntity;
import com.cursos.app.repository.IDistritoRepository;
import com.cursos.app.repository.IRolRepository;
import com.cursos.app.repository.IUsuarioRepository;
import com.cursos.app.rest.request.UsuarioRequest;
import com.cursos.app.rest.response.Paginacion;
import com.cursos.app.service.IUsuarioService;
import com.cursos.app.util.AdminTimeZone;
import com.cursos.app.util.Constantes;
import com.cursos.app.util.Date;
import com.cursos.app.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;

    private final IDistritoRepository distritoRepository;
    private final IRolRepository rolRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository, IDistritoRepository distritoRepository, IRolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.distritoRepository = distritoRepository;
        this.rolRepository = rolRepository;
    }




    @Override
    public UsuarioDTO guardar(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public UsuarioDTO actualizar(UsuarioDTO usuarioDTO) {
        return null;
    }


    @Override
    public UsuarioDTO agregar(UsuarioRequest request){

        Optional<UsuarioEntity> optional = usuarioRepository.findByEmailAndStatus(request.getEmail(),Constantes.CREATED_STATUS);
        if(optional.isPresent()){
            return null;
        }

        Optional<DistritoEntity> optionalDistrito = distritoRepository.findById(request.getDistrito());
        DistritoEntity distrito = optionalDistrito.get();

        Optional<RolEntity> optionalRol = rolRepository.findById(request.getRol());
        RolEntity rol = optionalRol.get();

        UsuarioEntity u = new UsuarioEntity();
        u.setId(request.getId());
        u.setNombres(request.getNombres());
        u.setApellidos(request.getApellidos());
        u.setDni(request.getDni());
        u.setEmail(request.getEmail());
        u.setTelefono(request.getTelefono());
        String encorderPassword = this.passwordEncoder.encode(request.getPassword());
        u.setPassword(encorderPassword);
        u.setDistrito(distrito);
        u.setRol(rol);
        u.setStatus(Constantes.CREATED_STATUS);
        u.setCreateDate(Date.getCurrent(AdminTimeZone.TIME_ZONE_DEFAULT));
        UsuarioEntity usuario = usuarioRepository.save(u);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombres(usuario.getNombres());
        usuarioDTO.setApellidos(usuario.getApellidos());
        usuarioDTO.setDni(usuario.getDni());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setDistrito(usuario.getDistrito().getDescripcion());
        usuarioDTO.setRol(usuario.getRol().getDescripcion());
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO editar(UsuarioRequest request){

        Optional<UsuarioEntity> optional = usuarioRepository.findByIdAndStatus(request.getId(),Constantes.CREATED_STATUS);
        if(optional.isEmpty()){
            return null;
        }

        Optional<DistritoEntity> optionalDistrito = distritoRepository.findById(request.getDistrito());
        DistritoEntity distrito = optionalDistrito.get();

        Optional<RolEntity> optionalRol = rolRepository.findById(request.getRol());
        RolEntity rol = optionalRol.get();

        UsuarioEntity u = new UsuarioEntity();
        u = optional.get();
        u.setId(request.getId());
        u.setNombres(request.getNombres());
        u.setApellidos(request.getApellidos());
        u.setDni(request.getDni());
        u.setEmail(request.getEmail());
        u.setTelefono(request.getTelefono());
        u.setPassword(request.getPassword());
        u.setDistrito(distrito);
        u.setRol(rol);
       // u.setStatus(Constantes.CREATED_STATUS);
        u.setUpdateDate(Date.getCurrent(AdminTimeZone.TIME_ZONE_DEFAULT));

        UsuarioEntity editarUsuario = usuarioRepository.save(u);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(editarUsuario.getId());
        usuarioDTO.setNombres(editarUsuario.getNombres());
        usuarioDTO.setApellidos(editarUsuario.getApellidos());
        usuarioDTO.setDni(editarUsuario.getDni());
        usuarioDTO.setEmail(editarUsuario.getEmail());
        usuarioDTO.setTelefono(editarUsuario.getTelefono());
        usuarioDTO.setDistrito(editarUsuario.getDistrito().getDescripcion());
        usuarioDTO.setRol(editarUsuario.getRol().getDescripcion());
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO buscarPorId(int id) {
        Optional<UsuarioEntity> optional = usuarioRepository.findByIdAndStatus(id,Constantes.CREATED_STATUS);
        if(optional.isEmpty()){
            return null;
        }
        UsuarioEntity usuario = optional.get();
        UsuarioDTO usuarioDTO = mapDTO(usuario);
        return usuarioDTO;
    }

    @Override
    public List<UsuarioDTO> obtener(int pageNum, int pageSize) {

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<UsuarioEntity> usuarios = usuarioRepository.getUsuarioEntities(pageable,Constantes.CREATED_STATUS);
        List<UsuarioEntity> listaUsuarios = usuarios.getContent();
        return listaUsuarios.stream().map(this::mapDTO).collect(Collectors.toList());

    }
    @Override
    public Paginacion obtenerUsuarios(int pageNum, int pageSize, String orderBy, String sortDir) {
        Sort sort = ordenarPor(orderBy,sortDir);
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<UsuarioEntity> usuarios = usuarioRepository.getUsuarioEntities(pageable,Constantes.CREATED_STATUS);
        List<UsuarioEntity> listaUsuarios = usuarios.getContent();
        List<UsuarioDTO> contenido = listaUsuarios.stream().map(
                paginacion -> mapDTO(paginacion)).collect(Collectors.toList());

        Paginacion paginacion =  new Paginacion();
        paginacion.setPageNumber(usuarios.getNumber());
        paginacion.setPageSize(usuarios.getSize());
        paginacion.setData(contenido);
        paginacion.setTotalElements(usuarios.getTotalElements());
        paginacion.setTotalPages(usuarios.getTotalPages());
        paginacion.setLastRow(usuarios.isLast());
        return paginacion;
    }

    @Override
    public void eliminar(int id) {

        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        usuario.setStatus(Constantes.DELETED_STATUS);
        usuario.setDeleteDate(Date.getCurrent(AdminTimeZone.TIME_ZONE_DEFAULT));
        usuarioRepository.save(usuario);
    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }

    private UsuarioDTO mapDTO(UsuarioEntity a){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(a.getId());
        usuarioDTO.setNombres(a.getNombres());
        usuarioDTO.setApellidos(a.getApellidos());
        usuarioDTO.setDni(a.getDni());
        usuarioDTO.setEmail(a.getEmail());
        usuarioDTO.setTelefono(a.getTelefono());
        usuarioDTO.setDistrito(a.getDistrito().getDescripcion());
        usuarioDTO.setRol(a.getRol().getDescripcion());

        return usuarioDTO;
    }
    private UsuarioEntity mapClass(UsuarioRequest d){
        UsuarioEntity usuario = ObjectMapperUtils.map(d, UsuarioEntity.class);
        return usuario;
    }






}
