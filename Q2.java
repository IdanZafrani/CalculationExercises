package test;

public class Q2{
	public static double calc(){
		Expression x=new Div(new Number(10),new Number(2));
		Expression y=new Mul(new Number(2),new Minus(new Number(2),new Number(3)));
		Expression result=new Plus(x,y);
		return result.calculate();
	}
}