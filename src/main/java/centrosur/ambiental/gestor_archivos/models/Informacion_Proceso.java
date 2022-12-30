package centrosur.ambiental.gestor_archivos.Models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Informacion_proceso")
public class Informacion_Proceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inf_proc_id", updatable = true, nullable = false)
    private long id;
    
    @Column(name = "inf_proc_titulo")
    private String titulo;

    @Column(name = "inf_proc_archivo", unique = true, nullable = false)
    private String arch_adjunto;

    // @Column(name = "inf_proc_archivo_final", unique = false)
    // private String arch_final;

    @Column(name = "inf_proc_descripcion", nullable = false)
    private String descripcion;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "proc_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Proceso proceso;

    public Informacion_Proceso(){}

    public long getId() {
        return id;
    }

    // public void setId(long id) {
    //     this.id = id;
    // }187,1

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArch_adjunto() {
        return arch_adjunto;
    }

    public void setArch_adjunto(String arch_inicial) {
        this.arch_adjunto = arch_inicial;
    }

    // public String getArch_final() {
    //     return arch_final;
    // }

    // public void setArch_final(String arch_final) {
    //     this.arch_final = arch_final;
    // }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    @Override
    public String toString() {
        return "Informacion_Proceso [id=" + id + ", titulo=" + titulo + ", arch_inicial=" + arch_adjunto
                + ", descripcion=" + descripcion + ", proceso=" + proceso + "]";
    }    
}
