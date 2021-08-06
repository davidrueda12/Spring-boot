package mx.com.gm.servicio;

import mx.com.gm.domain.Persona;
import java.util.List;

public interface PersonaServicio {
    public List<Persona> listarPersonas();

    public void guardar(Persona persona);

    public void eliminar(Persona persona);

    public Persona encontrarPersona(Persona persona);
}
