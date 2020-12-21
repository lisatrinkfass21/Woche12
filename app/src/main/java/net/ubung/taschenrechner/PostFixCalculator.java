package net.ubung.taschenrechner;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostFixCalculator {

    private List<String> expression = new ArrayList<String>();
    private Deque<Double> stack = new ArrayDeque<>();

    public PostFixCalculator(List<String> postfix){
        this.expression = postfix;


    }
    public BigDecimal getResult(){
        for (int i = 0; i < expression.size(); i++){
            if(Character.isDigit(expression.get(i).charAt(0))){ // if current element is a number
                stack.addLast(Double.parseDouble(expression.get(i)));
            }
            else{
                double tempResult = 0;
                double temp;
                switch (expression.get(i)){
                    case "+": temp = stack.removeLast();
                            tempResult=stack.removeLast() + temp;
                            break;
                    case "-": temp = stack.removeLast();
                         tempResult = stack.removeLast() - temp;
                          break;
                    case "*": temp = stack.removeLast();
                        tempResult = stack.removeLast() * temp;
                        break;
                    case "/": temp = stack.removeLast();
                        tempResult = stack.removeLast() / temp;
                        break;
                    default: tempResult = 0;
                    break;
                }
                stack.addLast(tempResult);
            }
        }
        return new BigDecimal(stack.removeLast()).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

}
