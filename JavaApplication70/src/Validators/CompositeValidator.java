/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;

import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author Lighthouse
 */
public class CompositeValidator<T> implements Validator<T> {
   private final List<Validator<T>> validators;
   
   public CompositeValidator(List<Validator<T>> validators){
      
      this.validators = validators;
      
   }
   
   @Override
   public List<String> validate(T info){
      List<String> errors = new ArrayList();
      
      for (Validator validator: validators) {
         errors.addAll(validator.validate(info));
      }
      
      return errors;
      
   }
   
}

