package centrosur.ambiental.gestor_archivos.Models;

import java.time.LocalDate;

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
@Table(name = "Registro_Actividad")
public class Registro_Actividad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rega_id", nullable = false)
    private Long id;

    @Column(name = "rega_estado")
    private boolean estado;

    // @Column(name = "rega_observacion")
    // private String observacion;

    @Column(name = "rega_fecha_realizado")
    private LocalDate fecha_realizado;

    @Column(name = "rega_nombre_doc", length = 100)
    private String nombre_documento;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "acg_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Actividad_General acti_general;

    public Registro_Actividad(){
        acti_general = new Actividad_General();
    }

    public Registro_Actividad(boolean estado, LocalDate fecha_realizado, String nombre_documento) {
        this.estado = estado;
        this.fecha_realizado = fecha_realizado;
        this.nombre_documento = nombre_documento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // public String getObservacion() {
    //     return observacion;
    // }

    // public void setObservacion(String observacion) {
    //     this.observacion = observacion;
    // }

    public LocalDate getFecha_realizado() {
        return fecha_realizado;
    }

    public void setFecha_realizado(LocalDate fecha_realizado) {
        this.fecha_realizado = fecha_realizado;
    }

    public String getNombre_documento() {
        return nombre_documento;
    }

    public void setNombre_documento(String nombre_documento) {
        this.nombre_documento = nombre_documento;
    }

    public Actividad_General getActi_general() {
        return acti_general;
    }

    public void setActi_general(Actividad_General acti_general) {
        this.acti_general = acti_general;
    }

    @Override
    public String toString() {
        return "Registro_Actividad [id=" + id + ", estado=" + estado + ", fecha_realizado=" + fecha_realizado 
            + ", nombre_documento=" + nombre_documento + ", acti_general=" + acti_general + "]";
    }

}