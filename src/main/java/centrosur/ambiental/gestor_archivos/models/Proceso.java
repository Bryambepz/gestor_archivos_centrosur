package centrosur.ambiental.gestor_archivos.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Proceso")
public class Proceso implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proc_id", nullable = false, unique = true)
    private Long id;

    @Column( name = "proc_num_proceso")
    private Integer proceso;
    
    @Column( name = "proc_descripcion", length = 255, nullable = false)
    private String descripcion;
    
    @Column(name = "proc_act_plan_manejo_amb")
    private String confirmacion_actual;

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
    private String plan_acc;

    @Column(name = "proc_estado_contrato")
    private String estado_contrato;

    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinColumn(name = "desc_proy_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Descripcion_Proyecto desc_proyecto;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Informacion_Proceso> lista_informacion_proc = new HashSet<>();
    
    public Proceso(){
        this.lista_informacion_proc = new HashSet<>();
    }

    public Integer getProceso() {
        return proceso;
    }

    public void setProceso(Integer proceso) {
        this.proceso = proceso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConfirmacion_actual() {
        return confirmacion_actual;
    }

    public void setConfirmacion_actual(String confirmacion_actual) {
        this.confirmacion_actual = confirmacion_actual;
    }

    public String getNum_contrato() {
        return num_contrato;
    }

    public void setNum_contrato(String num_contrato) {
        this.num_contrato = num_contrato;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getConsultor() {
        return consultor;
    }

    public void setConsultor(String consultor) {
        this.consultor = consultor;
    }

    public String getCodigo_registro() {
        return codigo_registro;
    }

    public void setCodigo_registro(String codigo_registro) {
        this.codigo_registro = codigo_registro;
    }

    public LocalDate getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(LocalDate fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getPlan_acc() {
        return plan_acc;
    }

    public void setPlan_acc(String plan_acc) {
        this.plan_acc = plan_acc;
    }

    public String getEstado_contrato() {
        return estado_contrato;
    }

    public void setEstado_contrato(String estado_contrato) {
        this.estado_contrato = estado_contrato;
    }

    public Descripcion_Proyecto getDesc_proyecto() {
        return desc_proyecto;
    }

    public void setDesc_proyecto(Descripcion_Proyecto desc_proyecto) {
        this.desc_proyecto = desc_proyecto;
    }

    public Set<Informacion_Proceso> getLista_informacion_proc() {
        return lista_informacion_proc;
    }

    public void addInformacion(Informacion_Proceso inf_pr){
        this.lista_informacion_proc.add(inf_pr);
    }

    @Override
    public String toString() {
        return "\n--->Proceso [id=" + id + ", descripcion=" + descripcion + ", confirmacion_actual=" + confirmacion_actual
                + ", num_contrato=" + num_contrato + ", monto=" + monto + ", consultor=" + consultor
                + ", codigo_registro=" + codigo_registro + ", fecha_ini=" + fecha_ini + ", fecha_fin=" + fecha_fin
                + ", plan_acc=" + plan_acc + ", estado_contrato=" + estado_contrato + ", desc_proyecto=" + desc_proyecto
                + "]\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
}
