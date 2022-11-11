package com.cursos.app.security;

import com.cursos.app.entities.UsuarioEntity;
import com.cursos.app.repository.IUsuarioRepository;
import com.cursos.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UserDetailServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> optional = usuarioRepository.findOneByEmailAndStatus(username, Constantes.CREATED_STATUS);
        if(optional.isEmpty()){
            return null;
        }
        UsuarioEntity usuario = optional.get();
        return new UserDetailImpl(usuario);
    }
}
