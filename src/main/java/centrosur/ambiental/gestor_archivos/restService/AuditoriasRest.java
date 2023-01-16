package centrosur.ambiental.gestor_archivos.restService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            proy.setResponsable(p); 
            proy = proy_rep.save(proy);

            p.addProyecto(proy);
            pers_rep.save(p);
            System.out.println("aaaa > " + proy);
            System.out.println("\nper > " + p);
            return proy;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("errpor > " + e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "/getProyectos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Proyecto> getProyectosAll() {
        try {
            return proy_rep.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(path = "/actualizarProyecto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Proyecto actualizarProy(@RequestBody Proyecto proy, @RequestParam Long id) {
        try {
            Proyecto proyectoResp = proy;
            proy = proy_rep.findAll().stream().filter(f -> f.getId() == id).findFirst().get();

            proy.setNombre(proyectoResp.getNombre());
            proy.setFecha_creacion(proyectoResp.getFecha_creacion());

            proy = proy_rep.save(proy);

            return proy;
        } catch (Exception e) {
            // System.out.println(">>> errorProyActua " + e.getMessage());
            return null;
        }
    }

    @PostMapping(path = "/descripcion_proyecto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Descripcion_Proyecto agregarDesc(@RequestBody Descripcion_Proyecto desc_proy, @RequestParam String nombreP) {
        try {
            Proyecto pr = proy_rep.findAll().stream().filter(f -> nombreP.equals(f.getNombre())).findFirst().get();
            desc_proy.setProyecto(pr);
            desc_proy = desc_proy_rep.save(desc_proy);

            pr.addDescripcionProyecto(desc_proy);
            proy_rep.save(pr);
            return desc_proy;
            // return (pr != null) ? desc_proy_rep.save(desc_proy) : null;
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
            System.out.println("listad > " + DescByProy);
            return DescByProy;
        } catch (Exception e) {
            System.out.println("--> " + e.getMessage());
            return null;
        }
    }

    @PutMapping(path = "/actualizarDescripcion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Descripcion_Proyecto actualizarDescripcion(@RequestBody Descripcion_Proyecto desc_proy,
            @RequestParam Long id) {
        Descripcion_Proyecto desc_proyResp = desc_proy;
        desc_proy = desc_proy_rep.findAll().stream().filter(dp -> dp.getId() == id).findFirst().get();

        desc_proy.setIdentificador_desc(desc_proyResp.getIdentificador_desc());
        desc_proy.setFecha_emision(desc_proyResp.getFecha_emision());
        desc_proy.setCodigo_aar(desc_proyResp.getCodigo_aar());
        desc_proy.setAar(desc_proyResp.getAar());

        desc_proy_rep.save(desc_proy);
        return (desc_proy != null) ? desc_proy : null;
    }

    @PostMapping(path = "/proceso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Proceso crearProceso(@RequestBody Proceso proc, @RequestParam String identificadorProyecto) {
        try {
            Descripcion_Proyecto desc_p = desc_proy_rep.findAll().stream()
                    .filter(f -> identificadorProyecto.equals(f.getIdentificador_desc())).findFirst().get();

            proc.setDesc_proyecto(desc_p);
            desc_p.addProceso(proc);
            return (desc_p != null) ? proc_rep.save(proc) : null;
        } catch (Exception e) {
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
                    .get().getLista_procesos().stream().forEach(f -> {                        
                        procesosByDescP.add(f);
                    });

            return procesosByDescP;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(path = "/getProcesoEditar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Proceso getProcEditar(@RequestParam String proyecto, @RequestParam String licencia,
            @RequestParam Integer proceso) {
        try {
            Proceso procesoEditar = proy_rep.findAll().stream().filter(proy -> proy.getNombre().equals(proyecto))
                    .findFirst().get()
                    .getLista_desc_proy().stream().filter(des_p -> des_p.getIdentificador_desc().equals(licencia))
                    .findFirst().get()
                    .getLista_procesos().stream().filter(proc -> proc.getProceso() == proceso).findFirst().get();
            return procesoEditar;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(path = "/actualizarProceso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Proceso actualizarProceso(@RequestBody Proceso procesoAct, String proyecto, String licencia,Integer proceso) {
        try {
            Proceso procesoEditar = proy_rep.findAll().stream().filter(proy -> proy.getNombre().equals(proyecto))
                    .findFirst().get()
                    .getLista_desc_proy().stream().filter(des_p -> des_p.getIdentificador_desc().equals(licencia))
                    .findFirst().get()
                    .getLista_procesos().stream().filter(proc -> proc.getProceso() == proceso).findFirst().get();
            Long id = procesoEditar.getId();
            Descripcion_Proyecto des_proy = procesoEditar.getDesc_proyecto();
            procesoEditar = procesoAct;
            procesoEditar.setId(id);
            procesoEditar.setDesc_proyecto(des_proy);
            return proc_rep.save(procesoEditar);
        } catch (Exception e) {
            System.out.println("error >>>> \n" + e.getMessage());
            return null;
        }
    }

    @DeleteMapping(path = "/eliminarProceso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean eliminarProceso(String proyecto, String licencia, Integer proceso) {
        try {
            Descripcion_Proyecto desc_del = desc_proy_rep.findAll().stream()
                    .filter(f -> f.getIdentificador_desc().equals(licencia)).findFirst().get();
            Proceso proc = desc_del.getLista_procesos().stream().filter(p -> p.getProceso() == proceso).findFirst()
                    .get();
            desc_del.getLista_procesos().remove(proc);
            desc_proy_rep.save(desc_del);
            proc.setDesc_proyecto(null);
            proc_rep.delete(proc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping(path = "/AdjuntarInformacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Informacion_Proceso addInformacionProceso(@RequestBody Informacion_Proceso info_proc,
            @RequestParam Integer proceso, @RequestParam String id_descrip) {
        try {
            Proceso p = desc_proy_rep.findAll().stream()
                    .filter(desc_p -> desc_p.getIdentificador_desc().equals(id_descrip)).findFirst()
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
    public List<Informacion_Proceso> getInfoProc() {
        try {
            return info_rep.findAll();
        } catch (Exception e) {
            System.out.println("eer " + e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "/getInfoProcesoBy", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Informacion_Proceso> getInfoProcByProc(@RequestParam String id_descripcion,
            @RequestParam Integer proceso) {
        try {
            List<Informacion_Proceso> listado = new ArrayList<>();
            desc_proy_rep.findAll().stream().filter(desc -> desc.getIdentificador_desc().equals(id_descripcion))
                    .findFirst().get()
                    .getLista_procesos().stream().filter(proc -> proc.getProceso() == proceso).findFirst().get()
                    .getLista_informacion_proc().stream().forEach(f -> listado.add(f));
            ;
            return listado;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(path = "/actualizarInformacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Informacion_Proceso actualizarInformacion(@RequestBody Informacion_Proceso inf_proc) {
        try {
            Long id = inf_proc.getId();
            Informacion_Proceso info_procResp = inf_proc;
            inf_proc = info_rep.findAll().stream().filter(f -> f.getId() == id).findFirst().get();
            inf_proc.setTitulo(info_procResp.getTitulo());
            inf_proc.setArch_adjunto(info_procResp.getArch_adjunto());
            inf_proc.setDescripcion(info_procResp.getDescripcion());
            inf_proc = info_rep.save(inf_proc);
            return inf_proc;
        } catch (Exception e) {
            return null;
        }
    }

}
