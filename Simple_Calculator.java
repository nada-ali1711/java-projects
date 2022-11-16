import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ICalculator {

    int add(int x, int y);

    float divide(int x, int y) throws RuntimeException;
}


public class Calculator implements ICalculator{
    /* Implement your calculator class here*/
    public int add(int x,int y) {
          return x+y;
    } 
    public float divide(int x,int y) {
        if (y==0) {
            throw new RuntimeException("Error");
        }
        float z=x;
        return z/y;
    }
  
    
    public static void main(String[] args) {
        Calculator c = new Calculator();
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        String z = input.next();
        int y = input.nextInt();
        if(z.charAt(0)=='+') {
            System.out.println(c.add(x,y));
        }
        if(z.charAt(0)=='/') {
            try{  
                System.out.println(c.divide(x,y)); 
            }
            catch(RuntimeException error) {
            System.out.println(error.getMessage());
            } 
       }  
    }
}
