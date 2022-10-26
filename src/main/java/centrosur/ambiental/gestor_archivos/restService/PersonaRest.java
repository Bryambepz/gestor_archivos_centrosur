package centrosur.ambiental.gestor_archivos.restService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import centrosur.ambiental.gestor_archivos.Models.Persona;
import centrosur.ambiental.gestor_archivos.Repository.Persona_Repository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Usuario")
public class PersonaRest {

    @Autowired
    Persona_Repository Persona_Repository;

    @PostMapping("/registrarse")
    public Persona registrarse(@RequestBody Persona per) {
        try {
            return Persona_Repository.save(per);
        } catch (Exception e) {
            System.out.println("eer --> \n" + e.getMessage());
            return null;
        }
    }

    @GetMapping("/allUsers")
    public List<Persona> getUsers() {
        // Persona p = new Persona("2121", "bry", "pr", LocalDate.now(),
        // "sis", "nult", "bry1@12.com", "1234", true, "user");
        // try {
        // Persona_Repository.save(p);
        // } catch (Exception e) {
        // System.out.println("eer --> \n" + e.getMessage());
        // return null;
        // }
        return (List<Persona>) Persona_Repository.findAll();
    }

    @PostMapping("/login")
    public boolean addUser(@RequestParam String email, @RequestParam String password) {
        return Persona_Repository.findAll().stream().filter(p -> email.equals(p.getEmail()) && password.equals(p.getContrasenia()))
                .findFirst().isPresent();
    }

    
}
