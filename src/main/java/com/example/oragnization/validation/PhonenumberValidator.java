
package com.example.oragnization.validation; 
import jakarta.validation.ConstraintValidator; 
import jakarta.validation.ConstraintValidatorContext; 
public class PhonenumberValidator implements ConstraintValidator<Phonenumber, String>
  { 
	private final String PHONE_PATTERN="^(\\+91[\\-\\s]?)?[6-9][0-9]{9}$";
  
   @Override
   public boolean isValid(String phonenumber, ConstraintValidatorContext context)
{  if(phonenumber==null||phonenumber.isBlank()) {
	return false; 
}
return phonenumber.matches(PHONE_PATTERN);

}
   }

 