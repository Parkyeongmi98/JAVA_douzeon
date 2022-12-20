package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		/* 코드 작성 */

		System.out.println("숫자를 입력하세요: ");
		int i = scanner.nextInt();
		
		if(i%2 == 1) {
			int temp = 0;
			for(int a=1; a<=i; a+=2) {
			temp += a;
			}
			i=temp;
		} else {
			int temp = 0;
			for(int a=2; a<=i; a+=2) {
			temp += a;
			}
			i=temp;
		}
		System.out.println("결과 값: " + i);
		
		scanner.close();
	}
}
