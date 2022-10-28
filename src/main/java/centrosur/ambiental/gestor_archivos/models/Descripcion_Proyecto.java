package centrosur.ambiental.gestor_archivos.Models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Descripcion_proyecto")
public class Descripcion_Proyecto {
    
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desc_proy_id", nullable = false, unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "desc_proy_fecha_emision", nullable = false)
    private LocalDate fecha_emision;

    @Column(name = "desc_proy_codigo_dga", nullable = false)
    private String codigo_dga;

    @Column(name = "desc_proy_codigo_aar", nullable = false)
    private String codigo_aar;

    @Column(name = "desc_proy_aar", nullable = false)
    private String aar;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proy_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Proyecto proyecto;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Proceso> lista_procesos = new HashSet<>();

    public Descripcion_Proyecto(){
        this.proyecto = new Proyecto();
        this.lista_procesos = new HashSet<>();
    }

    public Descripcion_Proyecto(Integer id, LocalDate fecha_emision, String codigo_dga, String codigo_aar, String aar,
            Proyecto proyecto) {
        this.id = id;
        this.fecha_emision = fecha_emision;
        this.codigo_dga = codigo_dga;
        this.codigo_aar = codigo_aar;
        this.aar = aar;
        this.proyecto = proyecto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getCodigo_dga() {
        return codigo_dga;
    }

    public void setCodigo_dga(String codigo_dga) {
        this.codigo_dga = codigo_dga;
    }

    public String getCodigo_aar() {
        return codigo_aar;
    }

    public void setCodigo_aar(String codigo_aar) {
        this.codigo_aar = codigo_aar;
    }

    public String getAar() {
        return aar;
    }

    public void setAar(String aar) {
        this.aar = aar;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Descripcion_Proyecto setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        return this;
    }

    public Set<Proceso> getLista_procesos() {
        return lista_procesos;
    }

    public void addProceso(Proceso proceso){
        lista_procesos.add(proceso);
    }

    @Override
    public String toString() {
        return "Descripcion_Proyecto [id=" + id + ", fecha_emision=" + fecha_emision + ", codigo_dga=" + codigo_dga
                + ", codigo_aar=" + codigo_aar + ", aar=" + aar + ", proyecto=" + proyecto + "]";
    }


}
