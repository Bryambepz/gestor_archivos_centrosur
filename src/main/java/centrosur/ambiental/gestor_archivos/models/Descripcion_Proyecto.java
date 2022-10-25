package centrosur.ambiental.gestor_archivos.Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Descripcion_proyecto")
public class Descripcion_Proyecto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rega_id", nullable = false)
    private Long id;

    @Column(name = "desc_proy_fecha_emision")
    private LocalDate fecha_emision;

    @Column(name = "desc_proy_codigo_dga")
    private String codigo_dga;

    @Column(name = "desc_proy_codigo_aar")
    private String codigo_aar;

}
