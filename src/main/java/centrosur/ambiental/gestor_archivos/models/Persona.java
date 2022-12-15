package centrosur.ambiental.gestor_archivos.Models;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id", nullable = false)
    private Long id;

    @Column(name = "per_cedula", nullable = false, length = 10, unique = true)
    private String cedula;

    @Column(name = "per_nombres", nullable = false, length = 75)
    private String nombres;

    @Column(name = "per_apellidos", nullable = false, length = 75)
    private String apellidos;

    @Column(name = "per_cargo", nullable = false, length = 75)
    private String cargo;

    @Column(name = "per_email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "per_contrasenia", nullable = false, length = 255)
    private String contrasenia;

    @Column(name = "per_estado")
    private boolean estado;

    @Column(name = "per_rol")
    private String rol;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Actividad_General> lista_act_generales = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Proyecto> lista_proyectosAu = new HashSet<>();
    
    public Persona() {
        this.lista_act_generales = new HashSet<>();
        lista_proyectosAu = new HashSet<>();
    }
       
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getNombres() {
        return nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getCargo() {
        return cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
       
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getContrasenia() {
        return contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public boolean isEstado() {
        return estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public void addActividad(Actividad_General ac_gen) {
		lista_act_generales.add(ac_gen);
	}
    
    public Set<Actividad_General> getLista_act_generales() {
        return lista_act_generales;
    }

    public Set<Proyecto> getLista_proyectosAu() {
        return lista_proyectosAu;
    }

    public void addProyecto(Proyecto proyecto){
        lista_proyectosAu.add(proyecto);
    }
    
    @Override
    public String toString() {
        return "Persona [id=" + id + ", cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos
                + ", cargo=" + cargo + ", email=" + email + ", contrasenia=" + contrasenia + ", estado=" + estado
                + ", rol=" + rol + "]";
    }
    
}
