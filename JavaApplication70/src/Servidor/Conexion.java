/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Lighthouse
 */
public class Conexion extends Thread {
   Socket cliente = null;
   DataInputStream buffEntrada;
   DataOutputStream buffSalida;
   DataInputStream teclado;
   String username = "";
   public static Vector<Conexion> clientesConectados = new Vector();
   List<String> songnames = new ArrayList();
   List<String> songurls = new ArrayList();
   
   
   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }
   
   
   
   public Conexion(Socket cliente, DataInputStream buffEntrada, DataOutputStream buffSalida){
      this.cliente = cliente;
      this.buffEntrada = buffEntrada;
      this.buffSalida = buffSalida;
      clientesConectados.add(this);
      songnames.add("Supersonic Rocket Ship");
      songnames.add("Rubberband man");
      songurls.add("ftp://192.168.3.7:3721/6-DSIV/The%20Kinks%20-%20Supersonic%20Rocket%20Ship%20(Avengers_%20Endg(MP3_160K).mp3");
      songurls.add("ftp://192.168.3.7:3721/6-DSIV/Rubberband%20Man.mp3");
      
   }
   
   public void run(){
      try{
         Boolean done = true;
         System.out.println("Num: " + clientesConectados.size());
         while(done){
            String mensaje = buffEntrada.readUTF();
            if(mensaje.contains("Register:")){
               
            }
            else if(mensaje.contains("Login:")){
               System.out.println(mensaje);
               this.EnviarMensaje("");
            }
            else if(mensaje.contains("Add:")){
               
            }
            else if(mensaje.contains("Exit:")){
               
            }
            else{
               System.out.println("Invalid");
            }
            System.out.println("/////");
            done = !mensaje.equals("actually exit");
         }
         
      } catch(Exception e){
         
      };
   }
   
   public void EnviarMensaje(String mensaje){
      try{
         String userList = "";
         String namesList = "";
         String urlList = "";
         for (int i = 0; i < clientesConectados.size(); i++) {
            userList = userList + clientesConectados.get(i).getUsername()+",";
         }
         for (int i = 0; i < songnames.size(); i++) {
            namesList = namesList + songnames.get(i)+",";
            urlList = urlList + songurls.get(i)+",";
         }
         buffSalida.writeUTF(mensaje + ":$%:" + userList+":$%:"+namesList+":$%:"+urlList);
      } catch(Exception e){
         
      };
      
   }
   
   
   
}
