package centrosur.ambiental.gestor_archivos.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aar")
public class Aar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aar_id", nullable = false)
    private Long id;

    @Column(name = "aar_entrega")
    private String doc_ent_aar;

    @Column(name = "aar_fecha_ent")
    private LocalDate fecha_ent_aar;

    @Column(name = "aar_respuesta")
    private String doc_res_aar;

    @Column(name = "aar_fecha_res")
    private LocalDate fecha_res_aar;

    @Column(name = "aar_doc_apr")
    private String doc_apr_aar;

    @Column(name = "aar_fecha_apr")
    private LocalDate fecha_apr_aar;

    public Aar(){}
}
