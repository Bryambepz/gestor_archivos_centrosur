package centrosur.ambiental.gestor_archivos.restService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import centrosur.ambiental.gestor_archivos.Models.Descripcion_Proyecto;
import centrosur.ambiental.gestor_archivos.Models.Proceso;
import centrosur.ambiental.gestor_archivos.Models.Proyecto;
import centrosur.ambiental.gestor_archivos.Repository.Descripcion_Proyecto_Repository;
import centrosur.ambiental.gestor_archivos.Repository.Proceso_Repository;
import centrosur.ambiental.gestor_archivos.Repository.Proyecto_Repository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Auditorias")
public class AuditoriasRest {

    @Autowired
    Proyecto_Repository proy_rep;
    @Autowired
    Descripcion_Proyecto_Repository desc_proy_rep;

    @Autowired
    Proceso_Repository proc_rep;

    @PostMapping(path = "/proyecto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Proyecto crearProyecto(@RequestBody Proyecto proy) {
        try {
            System.out.println(proy);
            return proy_rep.save(proy);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    @PostMapping(path = "/descripcion_proyecto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Descripcion_Proyecto agregarDesc(@RequestBody Descripcion_Proyecto desc_proy, @RequestParam String nombreP) {
        try {
            Proyecto pr = proy_rep.findAll().stream().filter(f -> nombreP.equals(f.getNombre())).findFirst().get();
            desc_proy.setProyecto(pr);
            pr.addDescripcionProyecto(desc_proy);
            System.out.println(pr);
            System.out.println(desc_proy);
            return (pr != null) ? desc_proy_rep.save(desc_proy) : null;
        } catch (Exception e) {
            System.out.println(" err -> " + e.getMessage());
            return null;
        }
    }

    @PostMapping(path = "/proceso")
    public Proceso crearProceso(@RequestBody Proceso proc, @RequestParam Integer idDescProy) {
        try {
            Descripcion_Proyecto desc_p = desc_proy_rep.findAll().stream()
                    .filter(f -> idDescProy == f.getId().intValue()).findFirst().get();
            proc.setDesc_proyecto(desc_p);
            desc_p.addProceso(proc);
            return (desc_p != null) ? proc_rep.save(proc) : null;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
