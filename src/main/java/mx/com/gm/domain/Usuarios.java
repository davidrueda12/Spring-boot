package mx.com.gm.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "usuarios")
public class Usuarios implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Long id_usuario;

    @Column(unique = true)
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nombre;

    private String apellido;

    @NotEmpty
    private String email;

    @NotEmpty
    private Integer telefono;

    @OneToMany
    @JoinColumn(name = "id_usuarios")
    private List<Roles> roles;
}
