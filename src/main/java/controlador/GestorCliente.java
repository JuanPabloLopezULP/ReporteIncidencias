package controlador;

import modelo.Cliente;
import org.hibernate.Query;
import persistencia.ConfiguracionHibernate;

/**
 *
 * @author Juan Pablo Lopez
 */
public class GestorCliente extends Gestor {

    public GestorCliente() {
        if (sesion == null || !sesion.isOpen()) {
            sesion = ConfiguracionHibernate.openSession();
        }
    }

    public Cliente getClienteXCUIT(Long cuit) {
        try {

            Query consulta = sesion.createQuery("SELECT cliente FROM Cliente cliente WHERE cliente.cuit = :cuit");
            consulta.setParameter("cuit", cuit);

            Cliente cliente = (Cliente) consulta.uniqueResult();
            return cliente;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
