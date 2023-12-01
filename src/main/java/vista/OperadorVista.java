package vista;

import java.util.Scanner;
import modelo.DatosContacto;
import modelo.OperadorMesaAyuda;

/**
 *
 * @author Juan Pablo Lopez
 */
public class OperadorVista {

    public OperadorMesaAyuda cargarOperadorNuevo(int legajo) {
        OperadorMesaAyuda operador = new OperadorMesaAyuda();

        System.out.println("Ingrese el apellido del Operador de Mesa de Ayuda");
        operador.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del Operador de Mesa de Ayuda");
        operador.setNombre(new Scanner(System.in).nextLine());
        operador.setLegajo(legajo);

        DatosContacto datosContacto = new DatosContacto();

        cargarDatosContacto(datosContacto);

        operador.setDatosContacto(datosContacto);

        return operador;
    }

    public OperadorMesaAyuda modificarOperador(OperadorMesaAyuda operador, int legajo) {

        System.out.println("Ingrese el apellido del Operador de Mesa de Ayuda");
        operador.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del Operador de Mesa de Ayuda");
        operador.setNombre(new Scanner(System.in).nextLine());
        operador.setLegajo(legajo);

        System.out.println("Desea modificar los datos de contacto? S/N");
        String respuesta = new Scanner(System.in).nextLine();
        if (respuesta.toUpperCase().equals("S")) {

            DatosContacto datosContacto = new DatosContacto();

            cargarDatosContacto(datosContacto);

            operador.setDatosContacto(datosContacto);
        }
        return operador;
    }

    private void cargarDatosContacto(DatosContacto datosContacto) {

        System.out.println("Ingrese el celular del Operador de Mesa de Ayuda");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Operador de Mesa de Ayuda");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Operador de Mesa de Ayuda");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());
    }
}
