package modelo;

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
@Table(name = "sis_rep_inc_especialidad")
@Getter
@Setter
public class Especialidad extends EntidadId {

    @Column(length = 175, nullable = false)
    private String denominacion;

    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TecnicoEspecialidad> tecnicosEspecialidad;//1 a N

    public Especialidad() {
    }

    public Especialidad(String denominacion) {
        this.denominacion = denominacion;
    }
}
