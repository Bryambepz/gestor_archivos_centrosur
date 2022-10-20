package centrosur.ambiental.gestor_archivos.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.LocalDate;

import javax.persistence.Column;

@Entity
@Table(name = "Actividad_General")
public class Actividad_General {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acg_id", nullable = false)
    Long id;

    @Column(name = "acg_titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "acg_descripcion", nullable = false, length = 255)
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

    public Actividad_General() {
    }

}
 