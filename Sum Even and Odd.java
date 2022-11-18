import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[] sumEvenOdd(int[] array) {
    	/*
        	Implement your method here
        */
        
        int even=0;
        int odd=0;
        
        for (int i=0 ; i<array.length ; i++){
            if (array[i]%2==0)
                even+=array[i];
            else
                odd+=array[i];
        }
        
        int[] sumArray = new int [2];
        sumArray[0]=even;
        sumArray[1]=odd;
        System.out.println("[" + sumArray[0] + ", " + sumArray[1] + "]");
        return sumArray;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        int[] array = new int[s.length];
        
        if (s.length == 1 && s[0].isEmpty()){
            array = new int[]{};
        }
        else{
            for(int i=0 ; i<s.length ; ++i)
                array[i] = Integer.parseInt(s[i]);
        }
        int[] res = new Solution().sumEvenOdd(array);
        
        
    }
}
