package centrosur.ambiental.gestor_archivos.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Termino_Referencia")
public class Termino_Referencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teref_id", nullable = false)
    private Long id;

    @Column(name = "teref_doc_ent")
    private String docu_emtrega;

    @Column(name = "teref_fecha_ent")
    private LocalDate fecha_entrega;
    
    @Column(name = "teref_doc_apr")
    private String docu_aprovado;

    @Column(name = "teref_fecha_apr")
    private LocalDate fecha_aprovado;

    @Column(name = "teref_estado")
    private boolean estado;
    
    public Termino_Referencia(){}
}
