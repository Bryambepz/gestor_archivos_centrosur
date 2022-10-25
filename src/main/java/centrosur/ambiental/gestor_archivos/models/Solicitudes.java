package centrosur.ambiental.gestor_archivos.Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Solicitudes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soli_id", nullable = false)
    private Long id;

    @Column(name = "soli_documento")
    private String documento;

    @Column(name = "soli_fecha")
    private LocalDate fecha;

    @Column(name = "soli_num_factura")
    private Integer num_factura;

    @Column(name = "soli_valor")
    private double valor;

    @Column(name = "soli_doc_apr")
    private String documento_apr;

    @Column(name = "soli_fecha_apr")
    private LocalDate fecha_apr;

    
}
