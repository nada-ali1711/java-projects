import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int fibonacci(int n) {
    	// Implement your method here.
        int t1=0;
        int t2=1;
        int t3=1;
        if(n==1)
            return t1;
        else if(n==2)
            return t2;
        else{
            for (int i=2 ; i<n ; i++){
                t3=t1+t2;
                t1=t2;
                t2=t3;
            }
            return(t3);
        }
        
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int res = new Solution().fibonacci(n);
        System.out.println(res);
        
    }
}
