package centrosur.ambiental.gestor_archivos.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proy_id", nullable = false)
    private Long id;

    @Column(name = "proy_nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "proy_fecha_creacion")
    private LocalDate fecha_creacion;

    public Proyecto(){}
    
}
