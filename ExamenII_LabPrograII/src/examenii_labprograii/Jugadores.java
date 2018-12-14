/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenii_labprograii;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.IOException;
/**
 *
 * @author Jarfsoft
 */
public class Jugadores {
    private int codigoEquipo;
    private String nombre;
    private int dorsal;
    private String posicion;
    private int edad;
    private String nacionalidad;
    private RandomAccessFile jd;
    public Jugadores(){
        
        try{
            jd=new RandomAccessFile("Jugadores.lig","rw");
        }catch(FileNotFoundException e){
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    public void agregarJugador(int codigoEquipo,String nombre, int dorsal, String posicion, int edad, String nacionalidad) throws IOException{
        jd.seek(jd.length());
        jd.writeInt(codigoEquipo);
        jd.writeUTF(nombre);
        jd.writeInt(edad);
        jd.writeUTF(nacionalidad);
    }
    public void eliminarEquipoJugadores(int codigo) throws IOException{
        jd.seek(0);
        while(jd.getFilePointer()!=jd.length()){
            if(jd.readInt()==codigo){
                jd.seek(jd.getFilePointer()-4);
                jd.writeInt(0);
                jd.writeUTF("");
                jd.writeInt(0);
                jd.writeUTF("");
            }else{
                jd.readUTF();
                jd.readInt();
                jd.readUTF();
            }
        }
    }
}
