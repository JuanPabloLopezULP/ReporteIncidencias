package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan Pablo Lopez
 */
@Entity
@Table(name = "sis_rep_inc_cliente")
@Getter
@Setter
public class Cliente extends EntidadId {

    @Column(length = 150)
    private String razonSocial;

    @Column(nullable = false, unique = true)
    private long cuit;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClienteServicio> servicios;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReporteIncidencia> reportesIncidencia;//1 a N

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iddatoscontacto")
    private DatosContacto datosContacto;//1 a 1

    public void addServicio(ClienteServicio clienteServicio) {

        if (this.servicios == null) {

            this.servicios = new ArrayList<ClienteServicio>();
        }
        this.servicios.add(clienteServicio);
    }

    public void addReporteIncidencia(ReporteIncidencia reporteIncidencia) {

        if (this.reportesIncidencia == null) {

            this.reportesIncidencia = new ArrayList<ReporteIncidencia>();
        }
        this.reportesIncidencia.add(reporteIncidencia);
    }
}
