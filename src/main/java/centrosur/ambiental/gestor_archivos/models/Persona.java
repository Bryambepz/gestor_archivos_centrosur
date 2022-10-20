package centrosur.ambiental.gestor_archivos.models;

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

    @Column(name = "per_cedula", nullable = false, length = 10)
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

    @Column(name = "per_email", nullable = false, length = 255)
    private String email;

    @Column(name = "per_contrasenia", nullable = false, length = 255)
    private String contrasenia;

    @Column(name = "per_estado")
    private boolean estado;

    @Column(name = "per_rol")
    private String rol;

    public Persona() {}

    

}
