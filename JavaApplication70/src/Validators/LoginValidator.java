/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class LoginValidator implements Validator<Usuario>{
   @Override
   public List<String> validate(Usuario info){
      List<String> errors = new ArrayList();
      
      String password = info.getPassword();
      String username = info.getUsername();
      
      boolean capsflag = false;
      boolean specialflag = false;
      boolean numflag = false;
      
      for (int i = 0; i < password.length(); i++) {
         char passchar = password.charAt(i);
         if(Character.isUpperCase(passchar)){
            capsflag = true;
         }
         
         if (Character.isDigit(passchar)) {
            for (int j = 0; j < password.length(); j++) {
               //char passchar2 = password.charAt(i);
               if(Character.isDigit(passchar)){
                  numflag = true;
               }
            }
         }
         
         if(!Character.isAlphabetic(passchar) && !Character.isDigit(passchar)){
            for (int j = 0; j < password.length(); j++) {
               //char passchar2 = password.charAt(i);
               if(!Character.isAlphabetic(passchar) && !Character.isDigit(passchar)){
                  specialflag = true;
               }
            }
         }
         
         
      }
      
      if(username.isEmpty()){
         errors.add("El nombre de usuario no puede estar vacío");
      }
      if(password.isEmpty()){
         errors.add("La contraseña no puede estar vacía");
      }
      else if(password.length() < 8){
         errors.add("La contraseña debe ser mayor a 8 caracteres");
      }
      else if(password.length() > 12){
         errors.add("La contraseña debe ser menor a 12 caracteres");
      }
      else if(!capsflag || !numflag || !specialflag){
         errors.add("La contraseña debe contener al menos 1 mayuscula, 2 numeros y 2 caracteres especiales");
      }
      
      
      
      return errors;
      
   }
}
