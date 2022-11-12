import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public double average(int[] array) {
        double sum=0;
        int l=array.length;
        for (int i=0 ; i<l ; i++){
            sum+=array[i];
        }
        double average = sum/l;
        return average;
        
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String sin= sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        int[] array = new int[s.length];
        
        if (s.length == 1 && s[0].isEmpty()){
            array = new int[]{};
            System.out.println(0.0);
        }
        else {
            for(int i = 0; i < s.length; ++i)
               array[i] = Integer.parseInt(s[i]);
            double newAverage = new Solution().average(array);
            System.out.println(newAverage);
        }
        
        
        
    }
}
