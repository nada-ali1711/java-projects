import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[] reverse(int[] array){
    
      /*Implement your reverse method here*/
        int temp;
        int i =0 ;
        int j = array.length-1;
        
        while (i<j){
            temp =array[i];
            array[i]=array[j];
            array[j]=temp;
            i++;
            j--;
        }
        return (array);
    
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
    	String[] s = sin.split(", ");;
		int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for(int i = 0; i < s.length; ++i)
        	   arr[i] = Integer.parseInt(s[i]);
        }
      	int[] res = new Solution().reverse(arr);
     	System.out.print("[");
      	for(int i = 0; i < arr.length; ++i) {
        	System.out.print(arr[i]);
            if(i != s.length - 1)
              System.out.print(", ");
        }
        System.out.print("]");
    }
}
