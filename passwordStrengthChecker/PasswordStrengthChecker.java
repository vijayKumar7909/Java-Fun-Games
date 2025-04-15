import java.util.Scanner;

public class PasswordStrengthChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        scanner.close();

        int strength = checkPasswordStrength(password);
        
        switch (strength) {
            case 1:
                System.out.println("Your Password strength is Weak");
                break;
            case 2:
                System.out.println("Your Password strength is Weak");
                break;
            case 3:
                System.out.println("Your Password strength is Medium");
                break;
            case 4:
                System.out.println("Your Password strength is Medium");
                break;
             case 5:
                System.out.println("Your Password strength is Strong");
                break;
            default:
            
                System.out.println("Invalid password");
                break;
        }
    }

    public static int checkPasswordStrength(String password) {
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;
        boolean has8Digits = false;

        if (password.length() < 8) {
            return 0; // Password is too short (invalid)
        }
        
        
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i); 

            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }

            else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } 
            
            else if (Character.isDigit(c)) {
                hasDigit = true;
            } 
            
            /* else if(password.length() < 8){
                has8Digits = true;
             } */
             
            else {
               
               // here if the char doesn't belongs to any above condition then it will be a SpecialCharacter

                hasSpecialCharacter = true;
            }

            //  if(password.length() >= 8)
            //      has8Digits = true;

        }

        // Incrementing the strength by comparing 

        int strength = 0;
        if (hasUppercase) {
            strength++;
        }
        if (hasLowercase) {
            strength++;
        }
        if (hasDigit) {
            strength++;
        }
        if (hasSpecialCharacter) {
            strength++;
        }
        if (has8Digits) {
            strength++;
        }
        
        return strength;
    }
}
