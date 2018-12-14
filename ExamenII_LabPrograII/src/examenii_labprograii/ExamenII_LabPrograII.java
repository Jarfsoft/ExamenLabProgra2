/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenii_labprograii;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author Jarfsoft
 */
public class ExamenII_LabPrograII {
    public static void main(String[] args) throws IOException {
        Equipos eq=new Equipos();
        Jugadores jd=new Jugadores();
        Scanner leer=new Scanner(System.in);
        int op=0;
        while(op!=10){
            System.out.print("Liga nacional\n----------------------\n\n1.Agregar Equipo\n2.Modificar Equipo\n3.Eliminar Equipo\n4.Agregar Jugador\n5.Equipos\n6.Lista de jugadores\n7.Listar jugadores por equipo\n8.Jugadores de posicion\n9.Informacion de Equipos\n10.Salir\n\nSeleccion: ");
            op=leer.nextInt();
            switch(op){
                case 1:
                    System.out.print("\n\nNombre del equipo: ");
                    String nombre=leer.next();
                    System.out.print("Nombre dela ciudad: ");
                    String ciudad=leer.next();
                    System.out.print("Capacidad del estadio: ");
                    int capacidad=leer.nextInt();
                    eq.agregarEquipo(nombre, ciudad, capacidad);
                    break;
                case 2:
                    System.out.print("\n\nCodigo: ");
                    int codigo=leer.nextInt();
                    eq.modificarEquipo(codigo);
                    break;
                case 3:
                    System.out.print("\n\nCodigo: ");
                    int codigo1=leer.nextInt();
                    eq.eliminarEquipo(codigo1);
                    jd.eliminarEquipoJugadores(codigo1);
                    break;
                case 4:
                    System.out.print("Codigo del equipo: ");
                    int code=leer.nextInt();
                    if(!eq.buscarEquipo(code)){
                        System.out.println("\n\nNo se encontro equipo");
                        break;
                    }
                    System.out.print("Nombre del jugador: ");
                    String nombre1=leer.next();
                    System.out.print("Numero de dorsal: ");
                    int dorsal=leer.nextInt();
                    System.out.print("posicion: ");
                    String posicion=leer.next();
                    System.out.print("Edad: ");
                    int edad=leer.nextInt();
                    System.out.print("Nacionalidad: ");
                    String nacionalidad=leer.next();
                    jd.agregarJugador(code,nombre1, dorsal, posicion,edad,nacionalidad);
                    break;
            }
        }
    }
    
}
