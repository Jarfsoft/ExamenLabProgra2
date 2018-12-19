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
            System.out.print("Liga nacional\n----------------------\n\n1.Agregar Equipo\n2.Modificar Equipo\n3.Eliminar Equipo\n4.Agregar Jugador\n5.Equipos\n6.Lista de jugadores por edad\n7.Listar jugadores por equipo\n8.Jugadores de posicion\n9.Informacion de Equipos\n10.Salir\n\nSeleccion: ");
            op=leer.nextInt();
            int cant;
            switch(op){
                case 1:
                    System.out.print("\n\nNombre del equipo: ");
                    String nombre=leer.next();
                    cant=nombre.length();
                    for(int x=0;x<50-cant;x++){
                        nombre=nombre.concat("&");
                    }
                    System.out.print("Nombre de la ciudad: ");
                    String ciudad=leer.next();
                    cant=ciudad.length();
                    for(int x=0;x<50-cant;x++){
                        ciudad=ciudad.concat("&");
                    }
                    System.out.print("Capacidad del estadio: ");
                    int capacidad=leer.nextInt();
                    eq.agregarEquipo(nombre, ciudad, capacidad);
                    System.out.println("\n\nEquipo agregado con exito.");
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
                    System.out.println("\n\n");
                    int[][] equipos=new int[eq.cantidadEquipos()][2];
                    for(int x=0;x<equipos.length;x++){
                        equipos[x][0]=x+1;
                        equipos[x][1]=jd.cantidadJugadoresEquipo(x+1);
                    }
                    int temp;
                    int temp1;
                    for(int i=0;i<equipos.length;i++){
                        for(int j=1;j<equipos.length-i;j++){
                            if(equipos[j-1][1]<equipos[j][1]){
                                temp=equipos[j-1][0];
                                temp1=equipos[j-1][1];
                                equipos[j-1][0]=equipos[j][0];
                                equipos[j-1][1]=equipos[j][1];
                                equipos[j][0]=temp;
                                equipos[j][1]=temp1;
                            }
                        }
                    }
                    System.out.println("Codigo\t\tNombre\t\tCiudad\t\tCapacidad");
                    for(int x=0;x<equipos.length;x++){
                        eq.imprimirEquipo(equipos[x][0],false);
                    }
                    System.out.print("Codigo del equipo: ");
                    int code=leer.nextInt();
                    if(!eq.buscarEquipo(code)){
                        System.out.println("\n\nNo se encontro equipo");
                        break;
                    }
                    System.out.print("Nombre del jugador: ");
                    String nombre1=leer.next();
                    cant=nombre1.length();
                    for(int x=0;x<50-cant;x++){
                        nombre1=nombre1.concat("&");
                    }
                    System.out.print("Numero de dorsal: ");
                    int dorsal=leer.nextInt();
                    System.out.print("posicion: ");
                    String posicion=leer.next();
                    cant=posicion.length();
                    for(int x=0;x<50-cant;x++){
                        posicion=posicion.concat("&");
                    }
                    System.out.print("Edad: ");
                    int edad=leer.nextInt();
                    System.out.print("Nacionalidad: ");
                    String nacionalidad=leer.next();
                    cant=nacionalidad.length();
                    for(int x=0;x<50-cant;x++){
                        nacionalidad=nacionalidad.concat("&");
                    }
                    jd.agregarJugador(code,nombre1, dorsal, posicion,edad,nacionalidad);
                    break;
                case 5:
                    System.out.println("\n\n");
                    int[][] equipos1=new int[eq.cantidadEquipos()+1][2];
                    for(int x=0;x<equipos1.length;x++){
                        equipos1[x][0]=eq.codigoPosicion(x+1);
                        equipos1[x][1]=jd.cantidadJugadoresEquipo(x+1);
                    }
                    int temp0;
                    int temp9;
                    for(int i=0;i<equipos1.length-1;i++){
                        for(int j=1;j<equipos1.length-1-i;j++){
                            if(equipos1[j-1][1]<equipos1[j][1]){
                                temp0=equipos1[j-1][0];
                                temp9=equipos1[j-1][1];
                                equipos1[j-1][0]=equipos1[j][0];
                                equipos1[j-1][1]=equipos1[j][1];
                                equipos1[j][0]=temp0;
                                equipos1[j][1]=temp9;
                            }
                        }
                    }
                    for(int x=0;x<equipos1.length-1;x++){
                        for(int y=0;y<2;y++)
                            System.out.print(equipos1[x][y]+" ");
                        System.out.println("");
                    }
                    System.out.println("Codigo\t\tNombre\t\tCiudad\t\tCapacidad");
                    for(int x=0;x<equipos1.length-1;x++){
                        System.out.println(equipos1[x][0]);
                        
                            eq.imprimirEquipo(equipos1[x][0],false);
                    }
                    break;
                case 6:
                    System.out.println("\n\nJugdores por edad:\n");
                    jd.imprimirJugadores();
                    break;
                case 7:
                    System.out.println("\n\nEquipos, sus jugadores con su dorsal:\n");
                    for(int x=0;x<eq.cantidadEquipos();x++){
                        eq.imprimirEquipo(x+1, true);
                        System.out.println("\n");
                        jd.imprimirJugadoresEquipo(x+1);
                        System.out.println("\n\n");
                    }
                    break;
                case 8:
                    System.out.print("\n\nPosicion (No imprimira nada si no encuentra la posicion): ");
                    String pos=leer.next();
                    int cant1=pos.length();
                    for(int x=0;x<50-cant1;x++){
                        pos=pos.concat("&");
                    }
                    System.out.println("\n");
                    jd.imprimirJugadoresPosicion(pos);
                    break;
                case 9:
                    int[][] equipos2=new int[eq.cantidadEquipos()][2];
                    for(int x=0;x<equipos2.length;x++){
                        equipos2[x][0]=x+1;
                        equipos2[x][1]=eq.capacidadEquipo(x+1);
                    }
                    int temp3=0;
                    int temp4=0;
                    for(int i=0;i<equipos2.length;i++){
                        for(int j=1;j<equipos2.length-i;j++){
                            if(equipos2[j-1][1]<equipos2[j][1]){
                                temp3=equipos2[j-1][0];
                                temp4=equipos2[j-1][1];
                                equipos2[j-1][0]=equipos2[j][0];
                                equipos2[j-1][1]=equipos2[j][1];
                                equipos2[j][0]=temp3;
                                equipos2[j][1]=temp4;
                            }
                        }
                    }
                    System.out.println("Codigo\t\tNombre\t\tCiudad\t\tCapacidad");
                    for(int x=0;x<equipos2.length;x++){
                        eq.imprimirEquipo(equipos2[x][0],false);
                    }
                    break;
                    
                    
            }
            System.out.println("\n\n");
        }
    }
    
}
