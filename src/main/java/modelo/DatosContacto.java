package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan Pablo Lopez
 */
@Entity
@Table(name = "sis_rep_inc_datos_contacto")
@Getter
@Setter
public class DatosContacto extends EntidadId {

    private long telefono;
    
    private long celular;
    
    @Column(length = 75)
    private String email;

    public DatosContacto() {
    }

    public DatosContacto(long telefono, long celular, String email) {
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
    }
}
