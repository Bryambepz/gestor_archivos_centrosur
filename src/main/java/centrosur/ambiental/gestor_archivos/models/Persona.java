package centrosur.ambiental.gestor_archivos.Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column(name = "per_fecha_nacimiento", nullable = false)
    private LocalDate fecha_nacimiento;

    @Column(name = "per_profesion", nullable = false, length = 75)
    private String profesion;

    @Column(name = "per_direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "per_email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "per_contrasenia", nullable = false, length = 255)
    private String contrasenia;

    @Column(name = "per_estado")
    private boolean estado;

    @Column(name = "per_rol")
    private String rol;

    public Persona() {}

    public Persona(String cedula, String nombres, String apellidos, LocalDate fecha_nacimiento, String profesion,
            String direccion, String email, String contrasenia, boolean estado, String rol) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.profesion = profesion;
        this.direccion = direccion;
        this.email = email;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.rol = rol;
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

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    @Override
    public String toString() {
        return "Persona [id=" + id + ", cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos
                + ", fecha_nacimiento=" + fecha_nacimiento + ", profesion=" + profesion + ", direccion=" + direccion
                + ", email=" + email + ", contrasenia=" + contrasenia + ", estado=" + estado + ", rol=" + rol + "]";
    }

}
