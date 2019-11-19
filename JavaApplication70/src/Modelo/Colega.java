/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Lighthouse
 */
public abstract class Colega {
   public Socket cliente;
   public DataOutputStream buffSalida;
   public DataInputStream buffEntrada;
   //public DataInputStream teclado;
   public static String nombre;
   public String ip;
   public int puerto;
   
   public void RecibirDatos(){
      Thread hilo = new Thread(new Runnable(){
         @Override
         public void run(){
            try{
               while(true){
                  String mesgIn = buffEntrada.readUTF();
                  if(mesgIn.contains(":$%:")){
                     
                  }
               }
            } catch(Exception e){
               
            }
         }
      }); 
      hilo.start();
   }
   public void EscribirDatos(String mensaje) throws IOException {
      buffSalida.writeUTF(mensaje);
      buffSalida.flush();
   }
   
   //String t = " ";
}
