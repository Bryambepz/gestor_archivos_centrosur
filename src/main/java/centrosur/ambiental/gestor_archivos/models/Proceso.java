package centrosur.ambiental.gestor_archivos.Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Proceso")
public class Proceso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proc_id", nullable = false)
    private Long id;

    @Column( name = "proc_descripcion", length = 255, nullable = false)
    private String descripcion;
    
    @Column(name = "proc_act_plan_manejo_amb")
    private boolean confirmacion_actual;

    @Column(name = "proc_num_contrato", length = 20, nullable = false)
    private String num_contrato;
    
    @Column(name = "proc_monto", nullable = false)
    private double monto;
    
    @Column(name = "proc_nomb_consultor", length = 255, nullable = false)
    private String consultor;
    
    @Column(name = "proc_cod_registro")
    private String codigo_registro;
    
    @Column(name = "proc_fecha_inicio", nullable = false)
    private LocalDate fecha_ini;
    
    @Column(name = "proc_fecha_fin", nullable = false)  
    private LocalDate fecha_fin;
        
    @Column(name = "proc_plan_accion")
    private boolean plan_acc;

    @Column(name = "proc_estado_contrato")
    private boolean estado_contrato;

    
    public Proceso(){}
}
