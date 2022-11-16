import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IStack {
    public Object pop();
    public Object peek();
    public void push(Object element);
    public boolean isEmpty();
    public int size();
  
}

class Object{
    int value;
    Object next;
}

public class MyStack implements IStack {
    
    Object top = null;
    int size = 0;
    
    public Object pop(){
        Object h = new Object();
        h.value = top.value;
        top = top.next;
        size--;
        return h;
    }
    
    public Object peek(){
        return top;
    }
    
    public void push(Object element){
        element.next = top;
        top = element;
        size++;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public int size(){
        return size;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]","");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        MyStack op = new MyStack();
        
        if (s.length == 1 && s[0].isEmpty()){
            arr = new int[]{};
            
        }
        else{
            for ( int i=s.length -1 ; i>=0 ; --i){
                arr[i] = Integer.parseInt(s[i]);
                Object w = new Object();
                w.value =arr[i];
                op.push(w);
            }
        }
        
        Object h = new Object();
        String operation  = sc.nextLine();
        switch(operation){
            case "push":
                h.value = sc.nextInt();
                op.push(h);
                op.print();
                break;
            
            case "pop":
                if(op.isEmpty())
                    System.out.print("Error");
                else{
                    op.pop();
                    op.print();
                }
                break;
            
            case "peek":
                if(op.isEmpty())
                    System.out.print("Error");
                else{
                    h = op.peek();
                    System.out.println(h.value);
                }
                break;
                
            case "isEmpty":
                if(op.isEmpty())
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
                
            case "size":
                System.out.println(op.size);
                break;       
        }
    }
    
    public void print(){
        Object h = top;
        if(h == null){
            System.out.print("[]");
            return;
        }
        System.out.print("[" + h.value);
        while(h.next !=null){
            h = h.next;
            System.out.print(", " +h.value);
        }
        System.out.print("]");
    }
}
