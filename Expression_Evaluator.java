import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IExpressionEvaluator {
public String infixToPostfix(String expression);
  
  
/**
* Evaluate a postfix numeric expression, with a single space separator
* @param expression postfix expression
* @return the expression evaluated value
*/
  
public int evaluate(String expression);

}


public class Evaluator implements IExpressionEvaluator {
private Node head;
private Node top;
private int size;
    
static int length; 
static boolean logic;
static int a;
static int b;
static int c;
    
private class Node{
    Object data;
    Node next;
}
    
public Evaluator(){
    this.top = null;
    size=0;
}
    
static int[] arr= new int[100]; 
    
public int push(Object x){
    Node temp= new Node();
    if(temp==null)
        System.out.print("Error");
    
    temp.data= x;
    temp.next=top;
    top=temp;
    size++;
    return 0;
}

public void Logic(boolean x){
    if(x==true)
        System.out.println("True");
    else if(x==false)
        System.out.println("False");            
        
}
    
public Object pop() throws NullPointerException {
    Object value = top.data;
    top = top.next;
    size--;
    return value;
}
    
public Object peek(){
    
    return top.data;
}

public boolean isEmpty(){
    return top==null;
}

public int size(){
        return size;
}

public int Priority(char c) {
    if(c == '+' || c == '-') 
        return 1;
    else if(c == '*' || c == '/') 
        return 2;
    else if(c=='^')
        return 3;
    else 
        return 0;
}
    
public String infixToPostfix(String expression){
    String result = new String("");
    Evaluator stack = new Evaluator();
    for (int i = 0; i<expression.length(); ++i){
        char c = expression.charAt(i);
        if (Character.isLetterOrDigit(c))
            result += c;
        else if (c == '(')
            stack.push(c);
        else if (c == ')'){
            while (!stack.isEmpty() && (char) stack.peek() != '('){
                result += stack.pop();
            }
            if(stack.isEmpty()) {
                System.out.print("Error");
                System.exit(0);
            }
            stack.pop();
        }
        else{
            while (!stack.isEmpty() && Priority(c) <= Priority((char)stack.peek())){
                result +=(char) stack.pop();
            }
            stack.push(c);
        }
    }
      
    while (!stack.isEmpty()){
        if((char)stack.peek() == '('){
            return "Error";
        }
        result += stack.pop();
    }
    return result;
}

static int isOperand(char input) {
    if (input >= 65 && input <= 90 || input >= 97 && input <= 122) {
        return 1;
    }
    return 0;
}

public int value(char expression){
    int value=0;
    switch(expression){
            case 'a': value=(a); break;
            case 'b': value=(b); break;
            case 'c': value=(c); break;         
    }
    return value;
}

    
public int evaluate(String expression) {
    Evaluator st = new Evaluator();
    int x = 0;
    int y = 0;
    int res=0;
    
    char ch[] = expression.toCharArray();
    for(char c: ch) {
      
        if(isOperand(c)==1) 
            st.push((int)value(c));
        else if(isOperand(c)==0) {
            if(st.size()==1){
                y = (int)st.pop();
                x = st.push(0);
            }
            else if (st.size()>1){
                y = (int)st.pop();
                x = (int)st.pop();
            }
            
            switch(c) {
                    case '+': st.push(x+y); break;
                    case '-': st.push(x-y); break;
                    case '*': st.push(x*y); break;
                    case '/': st.push(x/y); break;
                    case '^': st.push((int)Math.pow(x,y)); break; 
            }
        }
    }
    return (int)st.pop();
}    

    
    public static void main(String[] args) {
    Evaluator x=new Evaluator();
    Scanner scan=new Scanner(System.in);
    String expression =scan.next().replace("^--","^").replace("((","(").replace("))",")");
    if(expression.startsWith("--")){
        expression=expression.replaceFirst("--","");
    }
    if(!(expression.startsWith("--"))&& expression.contains("--")){
        expression=expression.replaceAll("--","+");
    }
        
    if(((expression.startsWith("+"))||(expression.startsWith("*"))||(expression.startsWith("/"))||(expression.startsWith("^")))||((isOperand(expression.charAt(expression.length()-1))==0)&&expression.charAt(expression.length()-1) !=')')){
        System.out.print("Error");
        System.exit(0);
    }
    a =( Integer.parseInt(scan.next().substring(2)));
    b =( Integer.parseInt(scan.next().substring(2)));
    c =( Integer.parseInt(scan.next().substring(2)));
    String postfix=x.infixToPostfix(expression);
    
    if(postfix.equals("Error")){
            System.out.println(postfix);   
            System.exit(0);
    }
    else{
        System.out.println(postfix); 
        int res=x.evaluate(postfix);
        System.out.print(res);}
    }
}
