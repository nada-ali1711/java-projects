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
public class LinkedListQueue implements IQueue {

    static int length; 
    static int num;
    static boolean logic;
    static int maxSize;   
    private Node front , rear;

    private class Node{
        Object data;
        Node next;
        public Node(Object data){
            this.data=data;
            this.next=null;
        }
    } 
    
    public LinkedListQueue(){
        this.front = null;
        this.rear = null;        
        maxSize=0;
    }    


    public void enqueue(Object item)throws NullPointerException{ 
          Node temp=new Node (item);
          if(rear==null){
            this.rear=this.front=temp;  
          maxSize++;
          }
          else{
              temp.next=front;
              front=temp;
              maxSize++;
          }
      }

    public int size(){
        return maxSize;
    }


    public boolean isEmpty(){
        return rear==null && front==null;
    }


    public void printList(){
        Node n=front;
        int j=0;
        System.out.print("[");
        while(n!=null){
            System.out.print(n.data);
            n=n.next;
            if(j != length - 1)
                System.out.print(", ");
            j++;
        } 
        System.out.print("]"); 
     }


    public Object dequeue() throws NullPointerException{
        Node current=front;
        Node last=rear;
        if(front==null){
            System.out.println("Error");
            System.exit(0);
        }
        else if(front == rear){
            front =rear=null;
            maxSize--;
        }
        
        else{
            while(current.next !=rear)
                current=current.next;
        
            rear=current;
            rear.next=null;
            maxSize--;
       }
       if (front==null) {
            rear = null;
        }
        return last;
    }
    
    public static void main(String[] args) {
        LinkedListQueue list =new LinkedListQueue();
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
        
        for(int i=arr.length-1;i>=0;i--){
            list.enqueue(arr[i]);
        }
        String function=sc.next();
        switch(function){
            case "enqueue":{
                num=sc.nextInt();
                try{
                    list.enqueue(num);
                    length=list.size(); 
                    list.printList();
                }
                catch(NullPointerException e){
                    System.out.println("Error");
                }
                break;
            }
            
            case "dequeue":{
                try{
                    list.dequeue();
                    length=list.size();
                    list.printList();  
                }
                catch(NullPointerException e){
                    System.exit(0);
                }
                break;
            }
            case "isEmpty":{
                logic=list.isEmpty();
                if(logic==true){
                    System.out.println("True");
                }
                else{
                   System.out.println("False");
                }
                break;
            }
            
            case "size":{
                System.out.println(list.size());                
                break;
            }        
         }
    }
}
