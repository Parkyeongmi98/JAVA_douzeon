package prob02;

import java.util.Scanner;

public class CalcApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while( true ) {
			System.out.print( ">> " );
			String expression = scanner.nextLine();
			
			if( "quit".equals( expression ) ) {
				break;
			}
			
			String[] tokens = expression.split( " " );
			
			if( tokens.length != 3 ) {
				System.out.println( ">> 알 수 없는 식입니다.");
				continue;
			}
			
			int lValue = Integer.parseInt( tokens[ 0 ] );
			int rValue = Integer.parseInt( tokens[ 1 ] );
			
			
			
			/* 코드 작성 */
			Add add = new Add();
			Div div = new Div();
			Mul mul = new Mul();
			Sub sub = new Sub();
			
			int result = 0;
			if (tokens[2].equals("+")) {
				result = add.calculate(lValue, rValue);
				System.out.println(result);
			} else if (tokens[2].equals("-")) {
				result = div.calculate(lValue, rValue);
				System.out.println(result);
			} else if (tokens[2].equals("*")) {
				result = mul.calculate(lValue, rValue);
				System.out.println(result);
			} else if (tokens[2].equals("/")) {
				result = sub.calculate(lValue, rValue);
				System.out.println(result);
			}
			
			
			
		}
		
		scanner.close();
	}

}