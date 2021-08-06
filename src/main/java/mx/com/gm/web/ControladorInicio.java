package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private PersonaServicio personaServicio;
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        var personas = personaServicio.listarPersonas();
        log.info("ejecutando el controlador Spring MVC");
        log.info("Usuario que hizo login:" + user);
        model.addAttribute("personas", personas);
        return "index";
    }
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "agregar-modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if (errores.hasErrors()){
            return "agregar-modificar";
        }
        personaServicio.guardar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaServicio.encontrarPersona(persona);
        model.addAttribute("persona",persona);
        return "agregar-modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaServicio.eliminar(persona);
        return "redirect:/";
    }
}
