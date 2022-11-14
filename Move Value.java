import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
	public int[] moveValue(int[] array, int value) {
    	/*
        	Implement your method here
        */
        int l=array.length;
        int j=l-1;
        int temp;
        
        for(int i=0 ; i<l ; i++){
            if(i<j){
                if(array[j]==value){
                    j--;
                }
                if(array[i]==value){
                    temp=array[i];
                    for (int k=i ; k<j ; k++){
                        array[k]=array[k+1];
                        i=-1;
                    }
                    array[j]=temp;
                }
            }
            
        }
        return(array);
        
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]","");
        String[] s = sin.split(", ");
        int[] arr= new int[s.length];
        
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else{
            for(int i=0 ; i< s.length ; ++i)
                arr[i] = Integer.parseInt(s[i]);
        }
        
        int v = sc.nextInt();
        sc.close();
        int[] res = new Solution().moveValue(arr,v);
        
        System.out.print("[");
        for(int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i]);
            if(i != s.length - 1)
              System.out.print(", ");
        }
        System.out.print("]");
        
        
        
    }
}
