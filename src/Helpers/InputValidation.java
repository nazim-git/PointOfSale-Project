package Helpers;

import java.util.regex.Pattern;

public class InputValidation {
	
	public static boolean validateAlphabets(String name) {
		name = name.replace(" ", "");
		boolean allLetters = name.chars().allMatch(Character::isLetter);
		
		return allLetters;
	}
	
	public static boolean validateEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
	
	public static boolean validateNumbers(String input) 
    { 
		String regex = "\\d+";
                              
        Pattern pat = Pattern.compile(regex); 
        if (input == null) 
            return false; 
        return pat.matcher(input).matches(); 
    } 

}
