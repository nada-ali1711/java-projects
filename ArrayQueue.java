
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
interface IQueue {
  /*** Inserts an item at the queue front.*/
  public void enqueue(Object item);
  /*** Removes the object at the queue rear and returnsit.*/
  public Object dequeue();
  /*** Tests if this queue is empty.*/
  public boolean isEmpty();
  /*** Returns the number of elements in the queue*/
  public int size();
}

public class ArrayQueue implements IQueue {
    int maxSize = 100;
    int rear = maxSize-1;
    int front = 0;
    int count = 0;
    static Object item;
    
    int array[] = new int[maxSize];
  

    public boolean isEmpty() {
        return count==0;
    }

    public void enqueue(Object item) throws NullPointerException{
        rear = (rear+1) % maxSize;
        array[rear] = (int) item;
        count++;
    }

    public Object dequeue() {

        if(count == 0) {
            System.out.println("Error");
            System.exit(0);
        }
        
        Object sth = (Object) array[front];
        front = (front+1) % maxSize;
        count--;
        return sth;
    }

    public int size(){
        return count;
    }

    public void printList(){ 
     System.out.print("[");
        int i = rear;
      while(i >= front){
          System.out.print(array[i]);
          if(i != front){
              System.out.print(", ");
          }
          i--;
            }  
        System.out.print("]"); 
        }
    
    public static void main(String[] args) {
        ArrayQueue queue =new ArrayQueue();
        Scanner sc = new Scanner(System.in);
            String sin = sc.nextLine().replaceAll("\\[|\\]", "");
            String[] s = sin.split(", ");
            int[] arr = new int[s.length];
            if (s.length == 1 && s[0].isEmpty())
                 arr = new int[]{};
            else {
              for(int i = 0; i < s.length; ++i)
                 arr[i] = Integer.parseInt(s[i]);
            }
        for(int i=arr.length-1; i>=0; i--){
            queue.enqueue(arr[i]);
        }
        String function=sc.next();
        switch(function){
            case "enqueue":{
               item=sc.nextInt();
                try{
               queue.enqueue(item);
               queue.printList();
                }
                catch(NullPointerException e){
                    System.out.println("Error");
                }
                break;
                }
            case "dequeue":{
                try{
                queue.dequeue();
                queue.printList();
                }
                catch(NullPointerException e){
                   System.exit(0);
                }  
                break;
                }
            case "isEmpty":{
                boolean logic = queue.isEmpty();
                if(logic==true){
                    System.out.println("True");
                }
                else{
                   System.out.println("False");
                }
                break;
                }
            case "size":{
                System.out.println(queue.size());                
                break;
                }        
           }
      }
}
