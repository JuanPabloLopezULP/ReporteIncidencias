package controlador;

import modelo.OperadorMesaAyuda;
import org.hibernate.Query;
import persistencia.ConfiguracionHibernate;

/**
 *
 * @author Juan Pablo Lopez
 */
public class GestorOperador extends Gestor {

    public GestorOperador() {
        if (sesion == null || !sesion.isOpen()) {
            sesion = ConfiguracionHibernate.openSession();
        }
    }

    public OperadorMesaAyuda getOperadorXLegajo(int legajo) {
        try {

            Query consulta = sesion.createQuery("FROM OperadorMesaAyuda operador WHERE operador.legajo = :legajo");
            consulta.setParameter("legajo", legajo);

            OperadorMesaAyuda operador = (OperadorMesaAyuda) consulta.uniqueResult();
            return operador;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
