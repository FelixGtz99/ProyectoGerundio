/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MusicClient;
import Validators.Usuario;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Lighthouse
 */
public class Controlador implements Observer, ActionListener {

   public MusicClient modelo;
   public Menu menu;
   public Registro registro;
   public Ingreso ingreso;
   public Reproductor reproductor;

   public MusicClient getModelo() {
      return modelo;
   }

   public void setModelo(MusicClient modelo) {
      this.modelo = modelo;
   }

   public Menu getMenu() {
      return menu;
   }

   public void setMenu(Menu menu) {
      this.menu = menu;
      this.menu.exitBtn.addActionListener(this);
      this.menu.loginBtn.addActionListener(this);
      this.menu.registerBtn.addActionListener(this);

      this.menu.setVisible(true);
   }

   public Registro getRegistro() {
      return registro;

   }

   public void setRegistro(Registro registro) {
      this.registro = registro;
      this.registro.backBtn.addActionListener(this);
      this.registro.exitBtn.addActionListener(this);
      this.registro.registerBtn.addActionListener(this);

   }

   public Ingreso getIngreso() {
      return ingreso;
   }

   public void setIngreso(Ingreso ingreso) {
      this.ingreso = ingreso;
      this.ingreso.backBtn.addActionListener(this);
      this.ingreso.exitBtn.addActionListener(this);
      this.ingreso.loginBtn.addActionListener(this);

   }

   public Reproductor getReproductor() {
      return reproductor;
   }

   public void setReproductor(Reproductor reproductor) {
      this.reproductor = reproductor;
      this.reproductor.addBtn.addActionListener(this);
      this.reproductor.exitBtn.addActionListener(this);
      this.reproductor.nextBtn.addActionListener(this);
      this.reproductor.playBtn.addActionListener(this);
      this.reproductor.prevBtn.addActionListener(this);
      this.reproductor.removeBtn.addActionListener(this);
   }

   public void RecibirDatos() {
      Thread hilo = new Thread(new Runnable() {
         @Override
         public void run() {
            try {
               while (true) {
                  String mesgIn = modelo.getReceived();
                  //System.out.println("Controller: " + mesgIn);
                  if (mesgIn.contains(":$%:")) {
                     System.out.println("Recibido en el controlador: " + mesgIn);
                     String[] divMsg = mesgIn.split(Pattern.quote(":$%:"));
                     String[] songs = divMsg[1].split(",");
                     String[] urls = divMsg[2].split(",");
                     for (int i = 0; i < songs.length; i++) {
                        reproductor.myBox.addItem(songs[i]);
                     }
                  } 
                  //modelo.setReceived("");
               }
            } catch (Exception e) {

            }
         }
      });
      hilo.start();
   }
   @Override
   public void update(Observable o, Object o1){
      String mesgIn = modelo.getReceived();
                  System.out.println("Controller: " + mesgIn);
                  if (mesgIn.contains(":$%:")) {
                     System.out.println("Recibido en el controlador: " + mesgIn);
                     String[] divMsg = mesgIn.split(Pattern.quote(":$%:"));
                     String[] songs = divMsg[1].split(",");
                     String[] urls = divMsg[2].split(",");
                     for (int i = 0; i < songs.length; i++) {
                        reproductor.myBox.addItem(songs[i]);
                     }
                  }
   }
   @Override
   public void actionPerformed(ActionEvent e) {

      if (e.getActionCommand().equals("Salir")) {
         System.exit(0);
      } else if (e.getActionCommand().equals("Volver")) {
         menu.setVisible(true);
         registro.dispose();
         ingreso.dispose();
      } else if (e.getSource() == registro.registerBtn) {
         String nombre = registro.nameField.getText();
         String apellido = registro.lnameField.getText();
         String email = registro.emailField.getText();
         String user = registro.userField.getText();
         String password = new String(registro.passField.getPassword());
         Usuario info = new Usuario(nombre, apellido, email, user, password);
         List<String> errors = modelo.validate(info);
         String errordisplay = "";
         for (String error : errors) {
            errordisplay = errordisplay + error + "\n";
         }
         if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(null, errordisplay,
                    "Error de Registro", JOptionPane.WARNING_MESSAGE);
         } else {

         }
      } else if (e.getSource() == menu.loginBtn) {
         ingreso.setVisible(true);
         menu.dispose();
      } else if (e.getSource() == menu.registerBtn) {
         registro.setVisible(true);
         menu.dispose();
      } else if (e.getSource() == ingreso.loginBtn) {
         try {
            modelo.EscribirDatos("Login:" + ingreso.userField.getText() + ":" + new String(ingreso.passField.getPassword()));
            reproductor.setVisible(true);
            ingreso.dispose();
         } catch (IOException ex) {

         }
      }
   }

}
