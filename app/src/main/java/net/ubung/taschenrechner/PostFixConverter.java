package net.ubung.taschenrechner;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostFixConverter {
    private String infix;
    private Deque<Character> stack = new ArrayDeque<Character>();
    private List<String> postfix = new ArrayList<String>();

    public PostFixConverter(String expression){
        this.infix = expression;

    }

    private void convertExpression(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<infix.length(); i++){
           if(Character.isDigit(infix.charAt(i))){//überprüft ob char eine Zahl ist
               sb.append(infix.charAt(i));//Wenn ja wird sie zum sb hinzugefügt
               while((i+1) != infix.length() && (Character.isDigit(infix.charAt(i+1)) || infix.charAt(i+1) == '.')) {
                   sb.append(infix.charAt(++i));
               }// es wird soo lange zahlen zu sb hinzugefügt bis eine Operator kommt ( + * - / ( ) )
               postfix.add(sb.toString());
               sb.delete(0, sb.length());
           }else{
               inputToStack(infix.charAt(i)); // alle Operatoren kommen in den Stack
           }
        }
        clearStack();

    }

    private void inputToStack(char input){
       if(stack.isEmpty() || input == '('){
           stack.addLast(input);
       }else{
           if(input == ')'){
               while(!stack.getLast().equals('(')){
                   postfix.add(stack.removeLast().toString());
               }
               stack.removeLast();
           }else {
               if (stack.getLast().equals('(')) {
                   stack.addLast(input);
               } else {
                   while (!stack.isEmpty() && !stack.getLast().equals('(') && getPrecedence(input) <= getPrecedence(stack.getLast())) {
                       postfix.add(stack.removeLast().toString());
                   }
                   stack.addLast(input);
               }
           }
       }
    }

    private int getPrecedence(char op){
        switch(op){
            case '+':
            case '-':
                return 1;
            case '*':
            case'/':
                return 2;

        }
        return -1;
    }

    private void clearStack(){
        while(!stack.isEmpty())
        {
            postfix.add(stack.removeLast().toString());
        }

    }

    public List<String> getPostfixExpression(){
        convertExpression();
        return postfix;
    }
}
