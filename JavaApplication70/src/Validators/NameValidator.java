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
 * @author Lighthouse
 */
public class NameValidator implements Validator<Usuario> {
   
   @Override
   public List<String> validate(Usuario info){
      List<String> errors = new ArrayList();
      String name = info.getName();
      String surname = info.getSurname();
      
      if(name.isEmpty()){
         errors.add("El Nombre no puede estar vacío");
      }
      
      if(surname.isEmpty()){
         errors.add("El Apellido no puede estar vacío");
      }
      
      return errors;
        
   }
}
