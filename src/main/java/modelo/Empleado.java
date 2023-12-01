package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan Pablo Lopez
 */
@MappedSuperclass
@Getter
@Setter
public abstract class Empleado extends EntidadId {

    protected String apellido;

    protected String nombre;

    @Column(unique = true)
    protected int legajo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iddatoscontacto")
    protected DatosContacto datosContacto;// 1 a 1

    @Transient
    protected String nombreCompleto;

    public Empleado() {
    }

    public Empleado(String apellido, String nombre, int legajo, DatosContacto datosContacto) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.legajo = legajo;
        this.datosContacto = datosContacto;
    }

    public String getNombreCompleto() {
        return this.apellido + " " + this.nombre;
    }
}
