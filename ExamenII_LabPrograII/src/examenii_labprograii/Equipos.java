/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenii_labprograii;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Jarfsoft
 */
public class Equipos {
    private RandomAccessFile eq;
    
    public Equipos(){
        
        try{
            eq=new RandomAccessFile("data\\Equipos.lig","rw");
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    private void getCodigo() throws IOException{
        eq.seek(0);
        int code=0;
        while(eq.getFilePointer()!=eq.length()){
            code=eq.readInt();
            eq.readUTF();
            eq.readUTF();
            eq.readInt();
        }
        eq.writeInt(code);
    }
    
    public void agregarEquipo(String nombre, String ciudad, int capacidad) throws IOException{
        getCodigo();
        eq.writeUTF(nombre);
        eq.writeUTF(ciudad);
        eq.writeInt(capacidad);
    }
    
    public void eliminarEquipo(int codigo) throws IOException{
        eq.seek(0);
        while(eq.getFilePointer()!=eq.length()){
            if(eq.readInt()==codigo){
                eq.seek(eq.getFilePointer()-4);
                break;
            }
            eq.readUTF();
            eq.readUTF();
            eq.readInt();
        }
        if(eq.getFilePointer()==eq.length()){
            System.out.println("\n\nNo se encontro equipo.");
            return;
        }
        eq.writeInt(0);
        eq.writeUTF("");
        eq.writeUTF("");
        eq.writeInt(0);
        //Falta eliminar jugadores
    }
    
    public void modificarEquipo(int codigo) throws IOException{
        Scanner leer=new Scanner(System.in);
        eq.seek(0);
        long pos=0;
        while(eq.getFilePointer()!=eq.length()){
            if(eq.readInt()==codigo){
                pos=eq.getFilePointer();
                break;
            }
            eq.readUTF();
            eq.readUTF();
            eq.readInt();
        }
        if(eq.getFilePointer()==eq.length()){
            System.out.println("\n\nNo se encontro equipo");
            return;
        }
        int op=0;
        while(op!=4){
            eq.seek(pos);
            System.out.print("\n\nQue desea modificar?\n\n1.Nombre\n2.Ciudad\n3Capacidad\n4.Salir\n\nSeleccion: ");
            op=leer.nextInt();
            switch (op) {
                case 1:
                    System.out.print("\n\nNuevo nombre: ");
                    String nombre=leer.next();
                    eq.writeUTF(nombre);
                    System.out.print("\n\nModificacion exitosa.");
                    break;
                case 2:
                    System.out.print("Nueva ciudad: ");
                    String ciudad=leer.next();
                    eq.readUTF();
                    eq.writeUTF(ciudad);
                    System.out.print("\n\nModificacion exitosa.");
                    break;
                case 3:
                    System.out.print("Nueva capacidad: ");
                    int capacidad=leer.nextInt();
                    eq.readUTF();
                    eq.readUTF();
                    eq.writeInt(capacidad);
                    System.out.print("\n\nModificacion exitosa.");
                    break;
                default:
                    break;
            }
            System.out.println("\n\n");
        }
    }
    
    public void imprimirEquipos() throws IOException{
        eq.seek(0);
        System.out.println("Codigo\tNombre\n");
        while(eq.getFilePointer()!=eq.length()){
            if(eq.readInt()==0){
                eq.readUTF();
                eq.readUTF();
                eq.readInt();
            }else{
                eq.seek(eq.getFilePointer()-4);
            }
            System.out.println(eq.readInt()+"\t"+eq.readUTF());
            eq.readUTF();
            eq.readInt();
        }
    }
    public boolean buscarEquipo(int codigo) throws IOException{
        eq.seek(0);
        while(eq.getFilePointer()!=eq.length()){
            if(eq.readInt()==codigo)
                return true;
            eq.readUTF();
            eq.readUTF();
            eq.readInt();
        }
        return false;
        
    }
}
