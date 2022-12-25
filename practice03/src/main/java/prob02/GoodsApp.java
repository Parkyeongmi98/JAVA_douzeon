package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;
	// final -> 변수 대입이 최종적으로 끝났다.

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		// 상품 입력
		System.out.println("상품을 입력하세요.");
		for(int i = 0; i < COUNT_GOODS; i++) {
			String input = scanner.nextLine();
			String[] inputs = input.split(" ");
			goods[i] = new Goods(inputs[0], Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]));
		}
		System.out.println();

		// 상품 출력
		for(int i = 0; i < COUNT_GOODS; i++) {
			goods[i].show();
		}
		
		
		// 자원정리
		scanner.close();
	}
}
