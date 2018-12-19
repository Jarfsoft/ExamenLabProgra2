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
    private RandomAccessFile jd;
    public Jugadores(){
        
        try{
            jd=new RandomAccessFile("Jugadores.lig","rw");
        }catch(FileNotFoundException e){
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    public void agregarJugador(int codigoEquipo,String nombre, int dorsal, String posicion, int edad, String nacionalidad) throws IOException{
        jd.seek(0);
        while(jd.getFilePointer()!=jd.length()){
            if(codigoEquipo==jd.readInt()){
                jd.readUTF();
                if(dorsal==jd.readInt()){
                    System.out.println("\n\nNumero en uso por alguien del equipo.");
                    return;
                }
                
            }else{
                jd.readUTF();
                jd.readInt();
            }
            jd.readUTF();
            jd.readInt();
            jd.readUTF();
        }
        jd.writeInt(codigoEquipo);
        jd.writeUTF(nombre);
        jd.writeInt(dorsal);
        jd.writeUTF(posicion);
        jd.writeInt(edad);
        jd.writeUTF(nacionalidad);
        System.out.println("\n\nJugador Agregado con exito.");
    }
    public void eliminarEquipoJugadores(int codigo) throws IOException{
        jd.seek(0);
        while(jd.getFilePointer()!=jd.length()){
            if(jd.readInt()==codigo){
                jd.seek(jd.getFilePointer()-4);
                jd.writeInt(0);
                jd.writeUTF("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                jd.writeInt(0);
                jd.writeUTF("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                jd.writeInt(0);
                jd.writeUTF("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            }else{
                jd.readUTF();
                jd.readInt();
                jd.readUTF();
                jd.readInt();
                jd.readUTF();
            }
        }
    }
    public int cantidadJugadoresEquipo(int codigo) throws IOException{
        int cantidad=0;
        jd.seek(0);
        while(jd.getFilePointer()!=jd.length()){
            if(jd.readInt()==codigo)
                cantidad++;
            jd.readUTF();
            jd.readInt();
            jd.readUTF();
            jd.readInt();
            jd.readUTF();
        }
        return cantidad;
        
    }
    public int cantidadJugadores()throws IOException{
        jd.seek(0);
        int cant=0;
        while(jd.getFilePointer()!=jd.length()){
            jd.readInt();
            jd.readUTF();
            jd.readInt();
            jd.readUTF();
            jd.readInt();
            jd.readUTF();
            cant++;
        }
        return cant;
    }
    public void imprimirJugador(int pos, boolean dorsal)throws IOException{
        long posi=jd.getFilePointer();
        int act=0;
        String nomb;
        jd.seek(0);
        do{
            act++;
            jd.readInt();
            nomb=jd.readUTF().split("&")[0];
            
            if(pos==act){
                if(dorsal)
                    System.out.println(nomb+"\t\t"+jd.readInt());
                else{
                    System.out.println(nomb);
                    jd.readInt();
                }
                
            }else{
                jd.readInt();
            }
            jd.readUTF();
            jd.readInt();
            jd.readUTF();
            
        }while(act!=pos);
        jd.seek(posi);
    }
    public void imprimirJugadores()throws IOException{
        int[][] jugadores= new int[cantidadJugadores()][2];
        jd.seek(0);
        for(int x=0;x<jugadores.length;x++){
            jd.readInt();
            jd.readUTF();
            jd.readInt();
            jd.readUTF();
            jugadores[x][0]=x+1;
            jugadores[x][1]=jd.readInt();
            jd.readUTF();
        }
        int temp;
        int temp1;
        for(int i=0;i<jugadores.length;i++){
            for(int j=1;j<jugadores.length-i;j++){
                if(jugadores[j-1][1]<jugadores[j][1]){
                    temp=jugadores[j-1][0];
                    temp1=jugadores[j-1][1];
                    jugadores[j-1][0]=jugadores[j][0];
                    jugadores[j-1][1]=jugadores[j][1];
                    jugadores[j][0]=temp;
                    jugadores[j][1]=temp1;
                }
            }
        }
        for(int x=0;x<jugadores.length;x++){
            imprimirJugador(jugadores[x][0],false);
        }
    }
    public void imprimirJugadoresEquipo(int code)throws IOException{
        jd.seek(0);
        int pos=0;
        while(jd.getFilePointer()!=jd.length()){
            pos++;
            if(jd.readInt()==code)
                imprimirJugador(pos,true);
            jd.readUTF();
            jd.readInt();
            jd.readUTF();
            jd.readInt();
            jd.readUTF();
        }
    }
    public void imprimirJugadoresPosicion(String posicion) throws IOException{
        jd.seek(0);
        long loc;
        String nomb;
        while(jd.getFilePointer()!=jd.length()){
            loc=jd.getFilePointer();
            jd.readInt();
            jd.readUTF();
            jd.readInt();
            if(jd.readUTF().equals(posicion)){
                jd.seek(loc);
                jd.readInt();
                nomb=jd.readUTF().split("&")[0];
                System.out.println(nomb);
                jd.readInt();
                jd.readUTF();
            }
            jd.readInt();
            jd.readUTF();
        }
    }
}
