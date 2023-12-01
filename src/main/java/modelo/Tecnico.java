package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan Pablo Lopez
 */
@Entity
@Table(name = "sis_rep_inc_tecnico")
@Getter
@Setter
public class Tecnico extends Empleado {

    @Column (nullable = false)
    private String metodoContacto;
    
    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReporteIncidencia> reportesIncidencia;//1 a N

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TecnicoEspecialidad> tecnicoEspecialidades;// 1 a N

    public Tecnico() {
    }

    public Tecnico(String apellido, String nombre, int legajo, DatosContacto datosContacto) {
        super(apellido, nombre, legajo, datosContacto);
    }

    public void addEspecialidad(TecnicoEspecialidad tecnicoEspecialidad) {
        if (this.tecnicoEspecialidades == null) {
            
            this.tecnicoEspecialidades = new ArrayList<TecnicoEspecialidad>();
        }
        this.tecnicoEspecialidades.add(tecnicoEspecialidad);
    }
    
    public void addReporteIncidencia(ReporteIncidencia reporteIncidencia){
        if (this.reportesIncidencia == null) {
            
            this.reportesIncidencia = new ArrayList<ReporteIncidencia>();
        }
        this.reportesIncidencia.add(reporteIncidencia);
    }
}
