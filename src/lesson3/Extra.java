package lesson3;

import java.util.Scanner;
import java.util.regex.Matcher;

public class Extra {
   public static void main(String[] args) {
        String pass;
        boolean val;
        Scanner scanner=new Scanner(System.in);

        System.out.println("Print a password ");
        pass = scanner.next();
        val = check(pass);
        if (!val) System.out.println("Password is not valid");
        else System.out.println("Password is valid");
    }

    public static boolean check(String pass){
        String valString = "(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z])\\S{8,}";
        boolean res = pass.matches(valString);
        return res;
    }
}
