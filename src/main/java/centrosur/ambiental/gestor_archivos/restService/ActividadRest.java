package centrosur.ambiental.gestor_archivos.restService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import centrosur.ambiental.gestor_archivos.Models.Actividad_General;
import centrosur.ambiental.gestor_archivos.Models.Persona;
import centrosur.ambiental.gestor_archivos.Models.Registro_Actividad;
import centrosur.ambiental.gestor_archivos.Repository.Actividad_General_Repository;
import centrosur.ambiental.gestor_archivos.Repository.Persona_Repository;
import centrosur.ambiental.gestor_archivos.Repository.Registro_Actividad_Repository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Actividad")
public class ActividadRest {

    @Autowired
    Actividad_General_Repository actividad_gen;
    @Autowired
    Persona_Repository per_rep;
    @Autowired
    Registro_Actividad_Repository regis_act_rep;
    @Autowired
    Persona_Repository persona_rep;

    @PostMapping(path = "/ingresar_actividad", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Actividad_General ingresarActividad(@RequestBody Actividad_General ac_gen, @RequestParam String cedula) {
        System.out.println("a guardf");
        try {

            Persona p = per_rep.findAll().stream().filter(per -> cedula.equals(per.getCedula())).findFirst().get();
            // int id = 1;
            // long idl = id;
            // ac_gen.setId(idl);
            ac_gen.setPersona(p);
            System.out.println(ac_gen);
            ac_gen = actividad_gen.save(ac_gen);
            p.addActividad(ac_gen);
            persona_rep.save(p);
            return ac_gen;
        } catch (Exception e) {
            System.out.println(" --> " + e.getMessage());
            return ac_gen;
        }
    }

    @GetMapping(path = "/ListarporUsuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Actividad_General> ListarporUsuario(@RequestParam(name = "cedula") String persona) {
        try {
            List<Persona> p = persona_rep.findByCedula(persona);
            List<Actividad_General> listado = new ArrayList<>();
            p.get(0).getLista_act_generales().stream()
                    .forEach((a) -> {
                        System.out.println("la per" + persona + "-- \n" + a.getTitulo());
                        listado.add(a);
                    });

            return (!listado.isEmpty()) ? listado : null;
        } catch (Exception e) {
            System.out.println(" -- > " + e.getMessage());
            return null;
        }
    }

    @PostMapping(path = "/registrar_actividad", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Registro_Actividad registrarACtividad(@RequestBody Registro_Actividad reg_actividad,
            @RequestParam String actividad) {
        try {
            Actividad_General act_gen = actividad_gen.findAll().stream().filter(ac -> actividad.equals(ac.getTitulo()))
                    .findFirst().get();
            reg_actividad.setActi_general(act_gen);
            act_gen.addRegistroActividad(reg_actividad);
            return (act_gen != null) ? regis_act_rep.save(reg_actividad) : null;
        } catch (Exception e) {
            System.out.println(" --// " + e.getMessage());
            return null;
        }
    }

}
