package mx.com.gm.servicio;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Roles;
import mx.com.gm.domain.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")
@Slf4j
public class UsuariosService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuarios = usuarioDao.findByUsername(username);

        if(usuarios == null){
            throw new UsernameNotFoundException(username);
        }

        var rol =new ArrayList<GrantedAuthority>();

        for (Roles roles: usuarios.getRoles()){
            rol.add(new SimpleGrantedAuthority(roles.getNombreRol()));
        }
        return new User(usuarios.getUsername(), usuarios.getPassword(), rol);
    }
}
