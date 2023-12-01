package modelo;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan Pablo Lopez
 */
@Entity
@Table(name = "sis_rep_inc_operador_mesa_ayuda")
@Getter @Setter
public class OperadorMesaAyuda extends Empleado {
    
}
