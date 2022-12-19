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

import centrosur.ambiental.gestor_archivos.Models.Descripcion_Proyecto;
import centrosur.ambiental.gestor_archivos.Models.Informacion_Proceso;
import centrosur.ambiental.gestor_archivos.Models.Persona;
import centrosur.ambiental.gestor_archivos.Models.Proceso;
import centrosur.ambiental.gestor_archivos.Models.Proyecto;
import centrosur.ambiental.gestor_archivos.Repository.Descripcion_Proyecto_Repository;
import centrosur.ambiental.gestor_archivos.Repository.Informacion_Proceso_Repository;
import centrosur.ambiental.gestor_archivos.Repository.Persona_Repository;
import centrosur.ambiental.gestor_archivos.Repository.Proceso_Repository;
import centrosur.ambiental.gestor_archivos.Repository.Proyecto_Repository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Auditorias")
public class AuditoriasRest {

    @Autowired
    Persona_Repository pers_rep;
    @Autowired
    Proyecto_Repository proy_rep;
    @Autowired
    Descripcion_Proyecto_Repository desc_proy_rep;
    @Autowired
    Proceso_Repository proc_rep;
    @Autowired
    Informacion_Proceso_Repository info_rep;

    @PostMapping(path = "/proyecto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Proyecto crearProyecto(@RequestBody Proyecto proy, @RequestParam String cedulaLogin) {
        try {
            Persona p = pers_rep.findAll().stream().filter(per -> per.getCedula().equals(cedulaLogin)).findFirst().get();
            p.addProyecto(proy);
            proy.setResponsable(p);
            System.out.println(proy);
            return proy_rep.save(proy);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    @GetMapping(path = "/getProyectos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Proyecto> getProyectosAll() {
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

    @GetMapping(path = "/getDescripciones", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Descripcion_Proyecto> getDescripcionProyecto() {
        try {
            return desc_proy_rep.findAll();
        } catch (Exception e) {
            System.out.println("--> " + e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "/getDescripcionByProyecto", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Descripcion_Proyecto> DescripcionPorProyecto(@RequestParam String proyecto) {
        try {
            List<Descripcion_Proyecto> DescByProy = new ArrayList<>();
            proy_rep.findByNombre(proyecto).get(0).getLista_desc_proy().forEach(des -> DescByProy.add(des));
            System.out.println(DescByProy);
            return DescByProy;
        } catch (Exception e) {
            System.out.println("--> " + e.getMessage());
            return null;
        }
    }

    @PostMapping(path = "/proceso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Proceso crearProceso(@RequestBody Proceso proc, @RequestParam String identificadorProyecto) {
        try {
            Descripcion_Proyecto desc_p = desc_proy_rep.findAll().stream()
                    .filter(f -> identificadorProyecto.equals(f.getIdentificador_desc())).findFirst().get();

            proc.setDesc_proyecto(desc_p);
            desc_p.addProceso(proc);
            System.out.println("Proceso --> " + proc);
            return (desc_p != null) ? proc_rep.save(proc) : null;
        } catch (Exception e) {
            System.out.println("> " + proc + "\n> " + identificadorProyecto);
            System.out.println("errp --->\n" + e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "/getProcesos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Proceso> getProceso() {
        try {
            return proc_rep.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(path = "/getProcesosByProy", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Proceso> getProcesoByProyecto(@RequestParam String proy_desc) {
        try {
            List<Proceso> procesosByDescP = new ArrayList<>();
            desc_proy_rep.findAll().stream().filter(desc -> desc.getIdentificador_desc().equals(proy_desc)).findFirst()
                    .get().getLista_procesos().stream().forEach(f -> procesosByDescP.add(f));
            return procesosByDescP;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(path = "/AdjuntarInformacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Informacion_Proceso addInformacionProceso(@RequestBody Informacion_Proceso info_proc, @RequestParam Integer proceso, @RequestParam String id_descrip) {
        try {
            System.out.println(info_proc);
            Proceso p = desc_proy_rep.findAll().stream().filter(desc_p -> desc_p.getIdentificador_desc().equals(id_descrip)).findFirst()
                .get().getLista_procesos().stream().filter(pro -> pro.getProceso().equals(proceso)).findFirst()
                    .get();

            info_proc.setProceso(p);
            p.addInformacion(info_proc);
            return (p != null) ? info_rep.save(info_proc) : null;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(path = "/getInfoProceso", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Informacion_Proceso> getInfoProc(){
        try {
            return info_rep.findAll();
        } catch (Exception e) {
            System.out.println("eer " + e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "/getInfoProcesoBy", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Informacion_Proceso> getInfoProcByProc(@RequestParam String id_descripcion, @RequestParam Integer proceso){
        try {
            List<Informacion_Proceso> listado = new ArrayList<>();
            desc_proy_rep.findAll().stream().filter(desc -> desc.getIdentificador_desc().equals(id_descripcion)).findFirst().get()
                .getLista_procesos().stream().filter(proc -> proc.getProceso() == proceso).findFirst().get()
                    .getLista_informacion_proc().stream().forEach(f -> listado.add(f));;
            System.out.println(listado);
            return listado;
        } catch (Exception e) {
            return null;
        }
    }
}
