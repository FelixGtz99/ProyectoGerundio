/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Validators.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Lighthouse
 */
public class MusicClient extends Observable {
   
   public Socket cliente;
   public DataOutputStream buffSalida;
   public DataInputStream buffEntrada;
   //public DataInputStream teclado;
   public static String nombre;
   public String ip;
   public int puerto;
   public String received="";
   
   public void RecibirDatos(){
      Thread hilo = new Thread(new Runnable(){
         @Override
         public void run(){
            try{
               while(true){
                  String mesgIn = buffEntrada.readUTF();
                  if(mesgIn.contains(":$%:")){
                     System.out.println(mesgIn);
                     setReceived(mesgIn);
                     notifyObservers();
                     System.out.println("Recibido en el cliente: " + getReceived());
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
   
   public MusicClient(String ip, int puerto){
      this.puerto = puerto;
      this.ip = ip;
   }
   
   public void init(){
      try{
         cliente = new Socket(ip,puerto);
         buffSalida = new DataOutputStream(cliente.getOutputStream());
         buffEntrada = new DataInputStream(cliente.getInputStream());
         RecibirDatos();
      } catch(Exception e){
         
      }
   }

   public String getReceived() {
      return received;
   }

   public void setReceived(String received) {
      this.received = received;
   }
   
   
   
   public List<String> validate(Usuario info){
      List<Validator> validators = new ArrayList();
      validators.add(new NameValidator());
      validators.add(new LoginValidator());
      Validator comp = new CompositeValidator(validators);
      List<String> errors = comp.validate(info);
      return errors;
   }
}
