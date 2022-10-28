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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    @NotFound(action = NotFoundAction.IGNORE) 
    @JsonIgnore
    private Set<Descripcion_Proyecto> lista_desc_proy = new HashSet<>();

    public Proyecto(){
        this.lista_desc_proy = new HashSet<>();
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
    
    @Override
    public String toString() {
        return "Proyecto [id=" + id + ", nombre=" + nombre + ", fecha_creacion=" + fecha_creacion + "]";
    }
    
}
