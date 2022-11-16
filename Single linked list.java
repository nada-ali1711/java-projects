import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
    public void add(int index, Node element);
    /**
    * Inserts the specified element at the end of the list.
    * @param element
    */
    public void add(Node element);
    /**
    * @param index
    * @return the element at the specified position in this list.
    */
    public Node get(int index);

    /**
    * Replaces the element at the specified position in this list with the
    * specified element.
    * @param index
    * @param element
    */
    public void set(int index, Node element);
    /**
    * Removes all of the elements from this list.
    */
    public void clear();
    /**
    * @return true if this list contains no elements.
    */
    public boolean isEmpty();
    /**
    * Removes the element at the specified position in this list.
    * @param index
    */
    public void remove(int index);
    /**
    * @return the number of elements in this list.
    */
    public int size();
    /**
    * @param fromIndex
    * @param toIndex
    * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
    */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
    * @param o
    * @return true if this list contains an element with the same value as the specified element.
    */
    public boolean contains(Node o);
}
class Node {
    public int data;
    public Node next;
    public Node previous;
    Node(int x){
        data = x;
        next = null;
        previous = null;
    }
}
public class DoublyLinkedList implements ILinkedList{
    public Node head;
    public Node tail;
    public void printLinkedList() {
        System.out.print("[");
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if(current.next!=null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.print("]");
    }
    @Override
    public void add(int index, Node element) {
        Node current = head;
        if(index == 0) {
            head = element;
            element.next= current;
        }else {
            Node before = head;
            for(int i=0;i<index;i++) {
                before = current;
                current = current.next;
            }
            element.next = current;
            before.next = element;
        }
    }

    @Override
    public void add(Node element) {
        Node current = head;
        if(head==null) {
            Node newNode = new Node(element.data);
            head = newNode;
        }else {
            while (current.next != null) {
                current = current.next; // we'll loop until current.next is null
            }
            Node newNode = new Node(element.data);
            current.next = newNode;
        }
        
    }
    @Override
    public Node get(int index) {
        Node current = head;
        for(int i=0;i<index;i++) {
            current = current.next;
            if(current == null) {
                    System.out.println("Error");
                    System.exit(0);
                }
        }
        return current;
    }

    @Override
    public void set(int index, Node element) {
        Node current = head;
        for(int i=0;i<index;i++) {
            current = current.next;
        }
        current.data = element.data; 
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public void remove(int index) {
        Node current = head;
        if (head == null) {
        }else if(index == 0) {
            head = head.next;
        }else {
            Node before = head;
            for(int i=0;i<index;i++) {
                before = current;
                current = current.next;
                if(current == null) {
                    System.out.println("Error");
                    System.exit(0);
                }
            }
            if(current!=null) {
                before.next = current.next;
            }
        }
    }
    @Override
    public int size() {
        // TODO Auto-generated method stub
        Node current = head;
        int i=0;
        while(current!=null) {
            i++;
            current = current.next;
        }
        return i;
    }

    @Override
    public DoublyLinkedList sublist(int fromIndex, int toIndex) {
        Node current = head;
        DoublyLinkedList listout = new DoublyLinkedList();
        if(head == null) {
            System.out.println("Error");
            System.exit(0);
        }
        int i=0;
        for(i=0;i< fromIndex;i++) {
            current = current.next;
        }
        while(i<=toIndex && current!=null) {
            listout.add(current);
            i++;
            current = current.next;
        }
        return listout;
    }
    @Override
    public boolean isEmpty() {
        return (head == null);
    }
    @Override
    public boolean contains(Node o) {
        // TODO Auto-generated method stub
        Node current = head;
        if(current == null) {
            return false;
        }
        while(current!=null) {
            if(current.data==o.data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node x = new Node(0);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        DoublyLinkedList list = new DoublyLinkedList();
        if (s.length == 1 && s[0].isEmpty()) {}
        else {
            for(int i = 0; i < s.length; ++i) {
                x.data=Integer.parseInt(s[i]);
                list.add(x);
            }
        }
        String key = sc.nextLine();
        if(key.equals("add")) {
            x.data = sc.nextInt();
            list.add(x);
            list.printLinkedList();
        }else if(key.equals("addToIndex")){
            int index = sc.nextInt();
            if(index > list.size()) {
                System.out.println("Error");
                System.exit(0);
            }
            x.data = sc.nextInt();
            list.add(index, x);
            list.printLinkedList();
        }else if(key.equals("get")){
            int index = sc.nextInt();
            if(index >= list.size()|| index<0) {
                System.out.println("Error");
                System.exit(0);
            }
            System.out.println(list.get(index).data);
        }else if(key.equals("set")){
            int index = sc.nextInt();
            if(index >= list.size()|| index<0) {
                System.out.println("Error");
                System.exit(0);
            }
            x.data = sc.nextInt();
            list.set(index, x);
            list.printLinkedList();
        }else if(key.equals("clear")){
            list.clear();
            list.printLinkedList();
        }else if(key.equals("remove")){
            int removed = sc.nextInt();
            if(removed >= list.size()|| removed<0) {
                System.out.println("Error");
                System.exit(0);
            }
            list.remove(removed);
            list.printLinkedList();
        }else if(key.equals("isEmpty")){
            if(list.isEmpty()) {
                System.out.println("True");
            }else {
                System.out.println("False");
            }
        }else if(key.equals("size")){
            int index = list.size();
            System.out.println(index);
        }else if(key.equals("sublist")){
            int index1 = sc.nextInt();
            int index2 = sc.nextInt();
            if(index1 >= list.size()|| index2>=list.size()|| index1>index2) {
                System.out.println("Error");
                System.exit(0);
            }
            DoublyLinkedList sub = list.sublist(index1, index2);
            sub.printLinkedList();
        }else if(key.equals("contains")){
            x.data = sc.nextInt();
            if(list.contains(x)) {
                System.out.println("True");
            }else {
                System.out.println("False");
            }
        }
    }
}
