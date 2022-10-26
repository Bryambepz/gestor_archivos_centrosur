package centrosur.ambiental.gestor_archivos.restService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import centrosur.ambiental.gestor_archivos.Models.Actividad_General;
import centrosur.ambiental.gestor_archivos.Models.Persona;
import centrosur.ambiental.gestor_archivos.Repository.Actividad_General_Repository;
import centrosur.ambiental.gestor_archivos.Repository.Persona_Repository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Actividad")
public class ActividadRest {
    
    @Autowired
    Actividad_General_Repository actividad_gen;
    @Autowired
    Persona_Repository per_rep;

    @PostMapping("/ingresar_actividad")
    public Actividad_General ingresarActividad(@RequestBody Actividad_General ac_gen, @RequestParam String cedula){
        try {
            Persona p = per_rep.findAll().stream().filter(per -> cedula.equals(per.getCedula())).findFirst().get();

            ac_gen.setPersona(p);
            System.out.println(" Laa acti -----> " + ac_gen.toString());
            
            return (p != null)? actividad_gen.save(ac_gen):null;
            // return actividad_gen.save(ac_gen);            
        } catch (Exception e) {
            System.out.println(" --> " + e.getMessage());            
            return ac_gen;
        }
    }
}
