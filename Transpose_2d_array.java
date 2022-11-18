import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[][] transpose(int[][] array, int row) {
    	/*
        	Implement your method here
        */
        
        
        int[][] trans = new int [row][row];
        
        
        for (int i=0 ; i<row ; i++){
            for (int j=0 ; j<row ; j++)
                trans[i][j]=array[j][i];
        }
        return trans;
    
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int r=0, c=0;
        String sin = sc.nextLine();
        String R=sin.replace("[[","").replace("]]","").replace("], [", "@");
        String sinen= sin.replaceAll("\\[|\\]","");
        String[] A = sinen.split(", ");
        String[] s= R.split("@");
        int row=s.length;
        int i2 = 0;        
        int[][] arr = new int[row][row];
        if (A.length == 1 && A[0].isEmpty())
            System.out.print("[[]]");
        else{
            while(i2 != A.length){
                for(r=0 ; r<row ; ++r){
                    for(c=0 ; c<row ; c++){
                        arr[r][c]=Integer.parseInt(A[i2]);
                        i2++;
                    }
                }
            }
            int[][] res = new Solution().transpose(arr, row);
            System.out.println(Arrays.deepToString(new Solution().transpose(arr,row)));    
        }
        
        
        
    }
}
