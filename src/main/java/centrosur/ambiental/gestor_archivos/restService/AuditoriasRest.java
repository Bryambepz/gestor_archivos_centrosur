package centrosur.ambiental.gestor_archivos.restService;

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

import centrosur.ambiental.gestor_archivos.Models.Descripcion_Proyecto;
import centrosur.ambiental.gestor_archivos.Models.Informacion_Proceso;
import centrosur.ambiental.gestor_archivos.Models.Proceso;
import centrosur.ambiental.gestor_archivos.Models.Proyecto;
import centrosur.ambiental.gestor_archivos.Repository.Descripcion_Proyecto_Repository;
import centrosur.ambiental.gestor_archivos.Repository.Informacion_Proceso_Repository;
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
    @Autowired
    Informacion_Proceso_Repository info_rep;

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

    @GetMapping(path = "/getProyectos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Proyecto> getProyectosAll(){
        try {            
            return proy_rep.findAll();
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
    public Proceso crearProceso(@RequestBody Proceso proc, @RequestParam String identificadorProyecto) {
        try {
            Descripcion_Proyecto desc_p = desc_proy_rep.findAll().stream().filter(f -> identificadorProyecto.equals(f.getIdentificador_desc())).findFirst().get();

            proc.setDesc_proyecto(desc_p);
            desc_p.addProceso(proc);
            System.out.println("Proceso --> " + proc);
            return (desc_p != null) ? proc_rep.save(proc) : null;
        } catch (Exception e) {
            System.out.println("errp --->\n" + e.getMessage());
            return null;
        }
    }

    @PostMapping(path = "/AdjuntarInformacion" )
    public Informacion_Proceso addInformacionProceso(@RequestBody Informacion_Proceso info_proc, @RequestParam Long id_proceso){
        try {
            Proceso proc = proc_rep.findById(id_proceso).get();
            info_proc.setProceso(proc);
            proc.addInformacion(info_proc);
            System.out.println(" el --> \n" + info_proc);
            // System.out.println(" el --> \n" + proc.getLista_informacion_proc());
            return (proc != null) ? info_rep.save(info_proc) : null;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
