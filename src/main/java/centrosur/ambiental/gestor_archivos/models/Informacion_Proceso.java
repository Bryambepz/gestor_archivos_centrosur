package centrosur.ambiental.gestor_archivos.Models;

import java.time.LocalDate;

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
    
    @Column(name = "inf_proc_archivo", unique = true, nullable = false)
    private String ubi_archivo;

    @Column(name = "inf_proc_fecha", nullable = false)
    private LocalDate fechaArch;

    @Column(name = "inf_proc_descripcion", nullable = true)
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

    public void setId(long id) {
        this.id = id;
    }

    public String getUbi_archivo() {
        return ubi_archivo;
    }

    public void setUbi_archivo(String ubi_archivo) {
        this.ubi_archivo = ubi_archivo;
    }

    public LocalDate getFechaArch() {
        return fechaArch;
    }

    public void setFechaArch(LocalDate fechaArch) {
        this.fechaArch = fechaArch;
    }

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
        return "Informacion_Proceso [id=" + id + ", ubi_archivo=" + ubi_archivo + ", fechaArch=" + fechaArch
                + ", descripcion=" + descripcion + ", proceso=" + proceso + "]";
    }

}
