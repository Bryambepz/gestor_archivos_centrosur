package centrosur.ambiental.gestor_archivos.Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Servicios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serv_id", nullable = false)
    private Long id;

    @Column(name = "serv_doc_ser")
    private String docu_serv;

    @Column(name = "serv_fecha")
    private LocalDate fecha_serv;

    @Column(name = "serv_num_transferencia")
    private Integer num_trans;

    @Column(name = "serv_valor")
    private double valor;

    public Servicios(){}
}
