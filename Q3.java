package test;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.lang.Character;
import java.lang.Integer;

public class Q3{

      public static double calc(String expression) {
            Stack<String>calcStack=new Stack<>();
            Stack<Expression>checkingExp=new Stack<>();
            Queue<String>queue=new LinkedList<>();
            String[] out=expression.split("(?<=[-+*/()])|(?=[-+*/()])");
            for(String str:out){
                  if(isDoublenum(str))
                        queue.add(str);
                  else{
                        switch (str){
                              case "/":
                              case "*":
                              case "(":
                                    calcStack.push(str);
                                    break;
                              case "+":
                              case "-":
                                    while(!calcStack.isEmpty()&&(!calcStack.peek().equals("("))){
                                          queue.add(calcStack.pop());
                                    }
                                    calcStack.push(str);
                                    break;
                              case ")":
                                    while(!calcStack.peek().equals("(")){
                                          queue.add(calcStack.pop());
                                    }
                                    calcStack.pop();
                                    break;
                        }
                  }
            }
            while(!calcStack.isEmpty())
                  queue.add(calcStack.pop());

          for(String str:queue) {
                if (isDoublenum(str))
                      checkingExp.push(new Number(Double.parseDouble(str)));
                else {
                      Expression right = checkingExp.pop();
                      Expression left = checkingExp.pop();

                      switch (str) {
                                  case "/":
                                  checkingExp.push(new Div(left, right));
                                  break;
                                  case "*":
                                           checkingExp.push(new Mul(left, right));
                                           break;
                                     case "+":
                                           checkingExp.push(new Plus(left, right));
                                           break;
                                     case "-":
                                           checkingExp.push(new Minus(left, right));
                               }

                         }

                   }
             double result=Math.floor(checkingExp.peek().calculate());

             return Math.floor((checkingExp.peek().calculate()*1000))/1000;
            }

      private static boolean isDoublenum(String x){
            try {
                  Double.parseDouble(x);
                  return true;
            } catch (NumberFormatException e) {
                  return false;
            }

      }
	  
}