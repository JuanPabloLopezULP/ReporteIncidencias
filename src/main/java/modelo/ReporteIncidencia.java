package modelo;

import java.util.Date;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan Pablo Lopez
 */
@Entity
@Table(name = "sis_rep_inc_reporte_incidencia")
@Getter
@Setter
public class ReporteIncidencia extends EntidadId {

    @Column(nullable = false, unique = true)
    private String codigoReporte;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    @Column(nullable = false)
    private String descripcionProblema;

    @Column(nullable = false)
    private String tipoProblema;//basico, intermedio, complejo

    @ManyToOne
    @JoinColumn(name = "idservicio")
    private Servicio servicio;//N a 1

    @ManyToOne
    @JoinColumn(name = "idoperador")
    private OperadorMesaAyuda operador;//N a 1

    @ManyToOne
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente cliente;//N a 1

    @ManyToOne
    @JoinColumn(name = "idtecnico", nullable = false)
    private Tecnico tecnico;//N a 1

    private int tiempoEstimadoResolucion;//horas o minutos

    @Temporal(TemporalType.DATE)
    private Date fechaPosibleResolucion;

    private String estado;//pendiente, en proceso, resuelto, anulado

    private String observacionesTecnico;

    @ManyToOne
    @JoinColumn(name = "idespecialidad", nullable = false)
    private Especialidad especialidad;

    @Transient
    protected String informacionReporte;

    public String generarCodigo() {

        Random aleatorio = new Random();
        String alfa = "ABCDEFGH";
        String cadena = "";
        int numero;
        int forma;
        forma = (int) (aleatorio.nextDouble() * alfa.length() - 1 + 0);
        numero = (int) (aleatorio.nextDouble() * 999 + 1000);
        cadena = cadena + alfa.charAt(forma) + numero;
        this.codigoReporte = cadena;
        return cadena;
    }

    public String getInformacionReporte() {
        return "Codigo: " + this.codigoReporte + ", Titular: " + this.cliente.getRazonSocial() + ", Estado: " + this.estado + ", Servicio: " + this.servicio.getDenominacion() + ", Tecnico: " + this.getTecnico().getNombreCompleto() + ", Especialidad: " + this.getEspecialidad().getDenominacion();
    }
}
