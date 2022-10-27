package centrosur.ambiental.gestor_archivos.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name = "Actividad_General")
public class Actividad_General {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acg_id", nullable = false)
    Long id;

    @Column(name = "acg_titulo", nullable = false, length = 175, unique = true)
    private String titulo;
    
    @Column(name = "acg_descripcion", nullable = false, length = 255, unique = true)
    private String descripcion;
    
    @Column(name = "acg_frecuencia", nullable = false)
    private Integer frecuencia;
    
    @Column(name = "acg_observacion", nullable = false, length = 255)
    private String observacion;
    
    @Column(name = "acg_estado")
    private boolean estado;
    
    @Column(name = "acg_fecha_inicio", nullable = false)
    private LocalDate fecha_inicio;
    
    @Column(name = "acg_fecha_fin", nullable = false)
    private LocalDate fecha_fin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "per_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona responsable;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Registro_Actividad> lista_reg_actividad = new HashSet<>();
    
    public Actividad_General() {
        this.responsable = new Persona();
    }
    
    public Actividad_General(String titulo, String descripcion, Integer frecuencia, String observacion, boolean estado,
            LocalDate fecha_inicio, LocalDate fecha_fin) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.frecuencia = frecuencia;
        this.observacion = observacion;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Persona getPersona() {
        return responsable;
    }

    public void setPersona(Persona persona) {
        this.responsable = persona;
    }

    public Set<Registro_Actividad> getLista_reg_actividad() {
        return lista_reg_actividad;
    }

    public void addRegistroActividad(Registro_Actividad registroAct){
        lista_reg_actividad.add(registroAct);
        // return this;
    }
    @Override
    public String toString() {
        return "Actividad_General [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", frecuencia="
                + frecuencia + ", observacion=" + observacion + ", estado=" + estado + ", fecha_inicio=" + fecha_inicio
                + ", fecha_fin=" + fecha_fin + ", persona=" + responsable + "]";
    }

}
 