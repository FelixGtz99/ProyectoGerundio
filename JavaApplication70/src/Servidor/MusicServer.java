/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

/**
 *
 * @author Lighthouse
 */
public class MusicServer extends Mediator{
    
   public MusicServer(int puerto){
      this.puerto = puerto;
   }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MusicServer servidor = new MusicServer(2060);
        servidor.init();
        
    }

}
