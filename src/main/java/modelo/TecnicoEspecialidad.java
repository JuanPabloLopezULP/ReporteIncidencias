package modelo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan Pablo Lopez
 */
@Entity
@Table(name = "sis_rep_inc_tecnico_especialidad")
@Getter @Setter
public class TecnicoEspecialidad extends EntidadId implements Comparable<TecnicoEspecialidad>{

    public TecnicoEspecialidad(){}
    
    public TecnicoEspecialidad(Tecnico tecnico, Especialidad especialidad) {
        this.tecnico = tecnico;
        this.especialidad = especialidad;
    }
    
    @ManyToOne
    @JoinColumn(name = "idtecnico")
    private Tecnico tecnico;
    
    @ManyToOne
    @JoinColumn(name = "idespecialidad")
    private Especialidad especialidad;

    @Override
    public int compareTo(TecnicoEspecialidad o) {
        return new Integer(this.tecnico.getLegajo()).compareTo(o.getTecnico().getLegajo());
    }
}
