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
@Table(name = "sis_rep_inc_cliente_servicio")
@Getter
@Setter
public class ClienteServicio extends EntidadId {

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idservicio")
    private Servicio servicio;
}
