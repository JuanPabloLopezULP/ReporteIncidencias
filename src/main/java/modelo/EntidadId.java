package modelo;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Juan Pablo Lopez
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EntidadId implements Serializable {//representa la clave primaria

    @Id//PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)//PK autonumerica
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
