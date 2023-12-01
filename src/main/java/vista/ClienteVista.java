package vista;

import java.util.Scanner;
import modelo.Cliente;
import modelo.DatosContacto;

/**
 *
 * @author Juan Pablo Lopez
 */
public class ClienteVista {

    public Cliente cargarClienteNuevo(long cuit) throws Exception {

        Cliente cliente = new Cliente();

        cliente.setCuit(cuit);
        System.out.println("Ingrese la razon social del Cliente");
        cliente.setRazonSocial(new Scanner(System.in).nextLine());

        DatosContacto datosContacto = new DatosContacto();

        modificarDatosContacto(datosContacto);

        cliente.setDatosContacto(datosContacto);

        agregarServicios(cliente);

        return cliente;
    }

    public Cliente modificarCliente(Cliente cliente) throws Exception {

        System.out.println("Ingrese el CUIT del Cliente");
        cliente.setCuit(new Scanner(System.in).nextLong());
        System.out.println("Ingrese la razon social del Cliente");
        cliente.setRazonSocial(new Scanner(System.in).nextLine());

        System.out.println("Desea modificar tambien los datos de contacto? S/N");
        String respuesta = new Scanner(System.in).nextLine();
        if (respuesta.toUpperCase().equals("S")) {

            DatosContacto datosContacto = cliente.getDatosContacto();

            modificarDatosContacto(datosContacto);

            cliente.setDatosContacto(datosContacto);
        }

        agregarServicios(cliente);

        return cliente;
    }

    private void modificarDatosContacto(DatosContacto datosContacto) {

        System.out.println("Ingrese el celular del Cliente");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Cliente");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Cliente");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());
    }

    private void agregarServicios(Cliente cliente) throws Exception {
        System.out.println("Desea gestionar los servicios? S/N");
        String respuesta = new Scanner(System.in).nextLine();
        if (respuesta.toUpperCase().equals("S")) {

            ServicioVista vistaServicio = new ServicioVista();
            vistaServicio.cargarServiciosCliente(cliente);
        }
    }
}
