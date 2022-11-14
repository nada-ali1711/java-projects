
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
    //main method
    public void add(char letter1, char letter2);
    ///general method
    public ILinkedList add(SingleLinkedList polynomial1, SingleLinkedList polynomial2);
    public void clear(char letter);
    public ILinkedList set(char letter, int[][] array);
    public int eval(char letter,int value);
    
}

class Node {
    public int coeff;
    public int exp;
    public Node next;

    public Node(int coeff, int exp) {
        
        this.coeff = coeff;
        this.exp = exp;
        this.next = null;
    }

}

//
public class SingleLinkedList implements ILinkedList {
    public  Node head=null;
    static int lengthgeneral;
    static int place;
    static int num;
    static boolean logic;
    static boolean notprint;
    static int[][] arr = new int[lengthgeneral][2];
    public static SingleLinkedList poly1 = new SingleLinkedList();
    public static SingleLinkedList poly2 = new SingleLinkedList();
    public static SingleLinkedList poly3 = new SingleLinkedList();
    public static SingleLinkedList result = new SingleLinkedList();
    
    public int eval(char letter,int value){
        float res=0;
        if(letter=='A'){
           Node current=poly1.head;
            
            while(current != null){
                res= (float)( res + ((Integer)current.coeff).floatValue() *Math.pow(value,current.exp));
                current=current.next;
            }
        }
        else if(letter=='B'){
            Node current=poly2.head;
            while(current != null){
                res=(float) (res+((Integer)current.coeff).floatValue() *Math.pow(value,current.exp));
                current=current.next;
            }
        }
        else  if(letter=='C'){
            Node current=poly3.head;
            res=0;
            while(current != null){
                res=(float) (res+((Integer)current.coeff).floatValue() *Math.pow(value,current.exp));
                current=current.next;
            }
        }
        
        else if(letter=='R'){
            Node current=result.head;
            while(current != null){
                res=(float) (res+((Integer)current.coeff).floatValue() *Math.pow(value,current.exp));
                current=current.next;
            }
        }
        return(int) res;
    }    
               
    public void clear(char letter){
        if(letter=='A'){
            poly1.head=null;
            poly1.print();
        }
        
        else if(letter=='B'){
            poly2.head=null ;
            poly2.print();
        }
        
        else if(letter=='C'){
            poly3.head=null;
            poly3.print();
        }
        
        else{
            result.head=null;
            result.print();
        }
    }
    
    public int size(){
        int size=0;
        Node list= head;
        if(head==null)
            size=0;
        else{
            Node current=head;
            while(current!=null){
                current=current.next;
                size++;
            }  
        }
        return size;
    }

    
    public ILinkedList set(char letter, int[][] array){
        Node current=head;
        if(letter=='A'){
            for(int i=0;i<array.length;i++){
                poly1.insert(array[i][0],array[i][1]); 
            }
            return poly1;
        }
        
        else if(letter=='B'){
            for(int i=0;i<array.length;i++)
                poly2.insert(array[i][0],array[i][1]); 
            return poly2;
        }
        
        else if(letter=='C'){
            for(int i=0;i<array.length;i++)
                poly3.insert(array[i][0],array[i][1]); 
            return poly3;
        }
        
        else{
            for(int i=0;i<array.length;i++)
                result.insert(array[i][0],array[i][1]); 
            return result;   
        }
    }

    
    public void insert(int coeff,int expon){
        Node next = new Node(coeff,expon);
        next.next = null;
        if (head == null)
            head = next;
        else{
            Node last = head;
            while (last.next != null) 
                last = last.next;
            last.next = next;
        }
    }


    public void print(){
        Node link1=head;
        if(link1==null)
            System.out.println("[]");
        else{
            while(link1!=null){  
                if(link1.exp== 0)
                    System.out.print(link1.coeff);

                else if(link1.coeff==1)
                    System.out.print("x^"+link1.exp);

                else if(link1.exp==1)
                    System.out.print(link1.coeff+"x");                    

                else
                    System.out.print(link1.coeff+"x^"+link1.exp);

                link1=link1.next;                 
                if(link1!=null&&link1.coeff>0)
                    System.out.print("+");
            }
        }
    }
    
    public void print(char letter){
        if(letter=='A'){
            poly1.print();
            System.out.print("\n");   
        }
        
        else if(letter=='B'){
            poly2.print();
            System.out.print("\n");   
        }
        
        else if(letter=='C'){
            poly3.print(); 
            System.out.print("\n");
        }
        
        else{
            result.print();             
            System.out.print("\n");
        }
    }
    
    
    public void multiply(char letter1,char letter2){
        result.head=null;
        if((letter1=='A' && letter2=='B')||(letter1=='B' && letter2=='A'))
            multiply(poly1,poly2);
        
        else if(letter1=='A' && letter2=='A')
            multiply(poly1,poly1);
        
        else if((letter1=='A' && letter2=='C')||(letter1=='C' && letter2=='A'))
            multiply(poly1,poly3);
        
        else if((letter1=='C' && letter2=='B')||(letter1=='B' && letter2=='C'))
            multiply(poly3,poly2);        
        
        else if(letter1=='B' && letter2=='B')
            multiply(poly2,poly2);        
        
        else if(letter1=='C' && letter2=='C')
            multiply(poly3,poly3);        
         
    }

    
    static void removeDuplicates(SingleLinkedList start){
        Node ptr1, ptr2, dup;
        ptr1 = start.head;
        while (ptr1 != null && ptr1.next != null){
            ptr2 = ptr1;
            while (ptr2.next != null){
                if (ptr1.exp == ptr2.next.exp) {
                    ptr1.coeff = ptr1.coeff + ptr2.next.coeff;
                    dup = ptr2.next;
                    ptr2.next = ptr2.next.next;
                }
                else
                    ptr2 = ptr2.next;
            }
            ptr1 = ptr1.next;
        }
    }

    
    public ILinkedList multiply(SingleLinkedList polynomial1,SingleLinkedList polynomial2){
        Node ptr1, ptr2;
        ptr1 = polynomial1.head;
        ptr2 = polynomial2.head;
        notprint=false;
        
        if(ptr1 ==null||ptr1 ==null){
            notprint=true; 
            return null;
        }
        
        else {
            while (ptr1 != null) {
                while (ptr2 != null) {
                    int coeff, power;
                    coeff = ptr1.coeff * ptr2.coeff;
                    power = ptr1.exp + ptr2.exp;
                    if(coeff !=0)
                        result.insert(coeff, power);
                    ptr2 = ptr2.next;
                }
                ptr2 = poly2.head;
                ptr1 = ptr1.next;
            }
            removeDuplicates(result);
            return result;
        }
    }

    
    public void add(char letter1,char letter2){
        result.head=null;
        if((letter1=='A' && letter2=='B')||(letter1=='B' && letter2=='A'))
            result.add(poly1,poly2);
        
        else if(letter1=='A' && letter2=='A')
            result.add(poly1,poly1);
        
        else if((letter1=='A' && letter2=='C')||(letter1=='C' && letter2=='A'))
            result.add(poly1,poly3);
        
        else if((letter1=='C' && letter2=='B')||(letter1=='B' && letter2=='C'))
            result.add(poly3,poly2); 
        
        else if(letter1=='B' && letter2=='B')
            result.add(poly2,poly2);
        
        else if(letter1=='C' && letter2=='C')
            result.add(poly3,poly3);        
    }
    
    
    public ILinkedList add(SingleLinkedList polynomial1,SingleLinkedList polynomial2){
        Node ptr1, ptr2;
        ptr1 = polynomial1.head;
        ptr2 = polynomial2.head;
        int coeff, power;
        notprint=false;
        if(ptr1 ==null||ptr1 ==null){
            notprint=true;
            return null;
        }
        
        else{
            while(ptr1!=null&&ptr2!=null){
                if(ptr1.exp==ptr2.exp){
                    coeff = ptr1.coeff+ptr2.coeff;
                    power = ptr2.exp;
                    ptr1=ptr1.next;
                    ptr2=ptr2.next;
                    if(coeff != 0) 
                        result.insert(coeff,power);
                }
                
                else if(ptr1.exp>ptr2.exp){
                    coeff = ptr1.coeff;
                    power = ptr1.exp;
                    result.insert(coeff,power);
                    ptr1=ptr1.next;
                }
                
                else{
                    coeff = ptr2.coeff;
                    power = ptr2.exp;
                    result.insert(coeff,power);
                    ptr2=ptr2.next;
                }
            }
            
            if(ptr1==null && ptr2 != null) {
                coeff = ptr2.coeff;
                power = ptr2.exp;
                result.insert(coeff,power);
            }
            
            else if(ptr1!=null && ptr2 == null){
                coeff = ptr1.coeff;
                power = ptr1.exp; 
                result.insert(coeff,power);
            }
            return result;
        }                    
    }

    
    public void sub(char letter1,char letter2){
        result.head=null;
        
        if((letter1=='A' && letter2=='B')||(letter1=='B' && letter2=='A'))
            sub(poly1,poly2);
        
        else if(letter1=='A' && letter2=='A')
            sub(poly1,poly1);
        
        else if((letter1=='A' && letter2=='C')||(letter1=='C' && letter2=='A'))
            sub(poly1,poly3);
        
        else if((letter1=='C' && letter2=='B')||(letter1=='B' && letter2=='C'))
            sub(poly3,poly2);        
        
        else if(letter1=='B' && letter2=='B')
            sub(poly2,poly2);
        
        else if(letter1=='C' && letter2=='C')
            sub(poly3,poly3);
        
    }
    
    public ILinkedList sub(SingleLinkedList polynomial1,SingleLinkedList polynomial2){
        Node ptr1, ptr2;
        ptr1 = polynomial1.head;
        ptr2 = polynomial2.head;
        int coeff, power;
        notprint=false;
        
        if(ptr1 ==null||ptr1 ==null){
            notprint=true;
            return null;
        }
        
        else{
            while(ptr1!=null&&ptr2!=null){
                if(ptr1.exp==ptr2.exp){
                    coeff = ptr1.coeff-ptr2.coeff;
                    power = ptr2.exp;
                    ptr1=ptr1.next;
                    ptr2=ptr2.next;
                    if(coeff != 0)
                        result.insert(coeff,power);
                }
                
                else if(ptr1.exp>ptr2.exp){
                    coeff = ptr1.coeff;
                    power = ptr1.exp;
                    result.insert(coeff,power);
                    ptr1=ptr1.next;
                }
                
                else{
                    coeff = ptr2.coeff;
                    power = ptr2.exp;
                    result.insert(coeff,power);
                    ptr2=ptr2.next;
                }
            }
            
            if(ptr1==null && ptr2 != null){
                coeff = ptr2.coeff;
                power = ptr2.exp;
                result.insert(coeff,power);
            }
            
            else if(ptr1!=null && ptr2 == null){
                coeff = ptr1.coeff;
                power = ptr1.exp;
                result.insert(coeff,power);
            }
            
            return result;
        }               
    }
    
    

    public static void check(char letter) {
        if(letter=='A' || letter=='B' || letter=='C') {
            
        }
        else {
            System.out.println("Error");
            System.exit(0);
        }
    }
    
    
    public static void checkempty(char letter) {
        if(letter=='A') {
            if(poly1.head==null){
                System.out.println("Error"); 
                System.exit(0);
            }
        }
        
        else if(letter=='B') {
            if(poly2.head==null){
                System.out.println("Error");
                System.exit(0);
            }
        }
        
        else if(letter=='C') {
            if(poly3.head==null){
                System.out.println("Error"); 
                System.exit(0);
            }
        }
    }
    
    public static void checkoper(String letter) {
        if(((letter.equals("set"))||(letter.isEmpty())||((letter.equals("add")))||((letter.equals("mult")))||((letter.equals("clear")))||((letter.equals("eval")))||((letter.equals("sub")))||((letter.equals("print"))))){}
        
        else {
            System.out.println("Error");
            System.exit(0);
        }
    }
   

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        SingleLinkedList list=new SingleLinkedList();
        char letter1;
        char letter2;
        
        while(sc.hasNextLine()){
            String oper=sc.nextLine();
            checkoper(oper);
            
            switch(oper){
                    case "set":{
                        letter1=sc.nextLine().charAt(0);
                        check(letter1);
                        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
                        String[] s = sin.split(",");
                        int[][] arr = new int[s.length][2];
                        lengthgeneral=s.length;
                        int general=s.length-1;
                        
                        if (s.length == 1 && s[0].isEmpty())
                            arr = new int[][]{};
                        
                        else {
                            for(int i = 0; i < s.length; ++i){
                                arr[i][0] = Integer.parseInt(s[i]);
                                arr[i][1] = general--;
                            }
                        }
                        
                        list.set(letter1,arr);
                        checkempty(letter1);
                        break;
                    }
                    
                    case "print":{
                        letter1=sc.next().charAt(0);
                        check(letter1);
                        checkempty(letter1);
                        list.print(letter1);
                        break;
                    }
                    
                    case "clear":{
                        letter1=sc.next().charAt(0);
                        check(letter1);
                        checkempty(letter1);
                        list.clear(letter1);
                        break;
                    }
                    
                    case"eval":{
                        letter1=sc.next().charAt(0);
                        check(letter1);
                        checkempty(letter1);
                        int val=sc.nextInt();
                        int result=list.eval(letter1,val);
                        System.out.print(result);
                        break;
                    }
                    
                    case"mult":{
                        letter1=sc.next().charAt(0);
                        check(letter1);
                        checkempty(letter1);
                        letter2=sc.next().charAt(0);
                        check(letter2);
                        checkempty(letter2);
                        list.multiply(letter1,letter2);
                        list.print('R');
                        break;
                    }
                    
                    case"add":{
                        letter1=sc.next().charAt(0);
                        check(letter1);
                        checkempty(letter1);
                        letter2=sc.next().charAt(0);
                        check(letter2);
                        checkempty(letter2);
                        list.add(letter1,letter2);
                        list.print('R');
                        break;
                    }
                    
                    case"sub":{
                        letter1=sc.next().charAt(0);
                        check(letter1);
                        checkempty(letter1);
                        letter2=sc.next().charAt(0);
                        check(letter2);
                        checkempty(letter2);
                        list.sub(letter1,letter2);
                        list.print('R');
                        break;
                    }
                }
            }
    }
}
