package centrosur.ambiental.gestor_archivos.Models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proy_id", nullable = false)
    private Long id;

    @Column(name = "proy_nombre", nullable = false, length = 255, unique = true)
    private String nombre;

    @Column(name = "proy_fecha_creacion")
    private LocalDate fecha_creacion;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Descripcion_Proyecto> lista_desc_proy = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "per_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona responsable;

    public Proyecto(){
        this.lista_desc_proy = new HashSet<>();
        this.responsable = new Persona();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { 
        
        this.nombre = nombre;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Set<Descripcion_Proyecto> getLista_desc_proy() {
        return lista_desc_proy;
    }

    public void addDescripcionProyecto(Descripcion_Proyecto desc_proy){
        lista_desc_proy.add(desc_proy);
    }
    
    public Persona getResponsable() {
        return responsable;
    }

    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Proyecto [id=" + id + ", nombre=" + nombre + ", fecha_creacion=" + fecha_creacion + ", persona=" + responsable + "]";
    }

    public Long getId() {
        return id;
    }
    
}
