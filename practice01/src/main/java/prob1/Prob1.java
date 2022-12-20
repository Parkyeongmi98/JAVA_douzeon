package prob1;

import java.util.Scanner;

public class Prob1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );
		
		int i;
		/* 코드 작성 */
		System.out.println("수를입력하시오 : ");
		i = scanner.nextInt();
		
		if(i%3 == 0) {
			System.out.println("3의 배수입니다.");
		} else {
			System.out.println("3의 배수가 아닙니다.");
		}
		scanner.close();
	}
}
