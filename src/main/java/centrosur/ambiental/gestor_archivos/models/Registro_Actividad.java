package centrosur.ambiental.gestor_archivos.Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Registro_Actividad")
public class Registro_Actividad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rega_id", nullable = false)
    private Long id;

    @Column(name = "rega_estado")
    private boolean estado;

    @Column(name = "rega_observacion")
    private String observacion;

    @Column(name = "rega_fecha_realizado")
    private LocalDate fecha_realizado;

    @Column(name = "rega_nombre_doc", length = 100)
    private String nombre_documento;
    
    public Registro_Actividad(){}
}