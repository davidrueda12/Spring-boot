package mx.com.gm.dao;

import mx.com.gm.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuarios,Long> {
    Usuarios findByUsername(String username);

}
