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

    // // @Column(name = "proy_documento_entrega", length = 255)
    // // private String documento_entrega; 

    // @Column(name = "proy_fecha_entrega")
    // private LocalDate fecha_entrega;

    // // @Column(name = "proy_documento_aprovado")
    // // private String documento_aprovado

    // @Column(name = "proy_estado_doc_tdr")
    // private boolean estado_doc_tdr;

    // // @Column(name = "doc_entrega_aar")
    // @Column(name = "proy_fecha_ent_aar")
    // private LocalDate fecha_entrega_aar;
    
    // // @Column(name = "doc_respuesta_aar")
    // @Column(name = "proy_fecha_res_aar")
    // private boolean fecha_respuesta_aar;
    
    // @Column(name = "prt")
    
}
