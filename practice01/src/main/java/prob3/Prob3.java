package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		/* 코드 작성 */

		System.out.println("숫자를 입력하세요: ");
		int input = scanner.nextInt();
		int sum = 0;
		if (input % 2 == 1) {
			for (int i = 1; i <= input; i+=2) {
				sum += i;
			}
			System.out.println("결과 값 : " + sum);
		} else if ((input % 2 == 0)) {
			for (int i = 0; i <= input; i+=2) {
				sum += i;
			}
			System.out.println("결과 값 : " + sum);
		}
		
		scanner.close();
	}
}
