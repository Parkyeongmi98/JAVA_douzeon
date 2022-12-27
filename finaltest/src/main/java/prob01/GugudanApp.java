package prob01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class GugudanApp {

	static int resultNumber = 0;

	public static void main(String[] args) {
		int l = randomize(1, 9);
		int r = randomize(1, 9);

		resultNumber = l * r;

		int[] answerNumbers = randomizeAnswers();
		int loc = randomize(0, 8);
		answerNumbers[loc] = resultNumber;

		System.out.println(l + " x " + r + " = ?");

		int length = answerNumbers.length;
		for (int i = 0; i < length; i++) {
			if (i % 3 == 0) {
				System.out.print("\n");
			} else {
				System.out.print("\t");
			}

			System.out.print(answerNumbers[i]);
		}

		System.out.print("\n\n");
		System.out.print("answer:");

		Scanner s = new Scanner(System.in);
		int answer = s.nextInt();
		s.close();

		System.out.println((answer == resultNumber) ? "정답" : "오답");
	}

	private static int randomize(int lNum, int rNum) {
		int random = (int) (Math.random() * rNum) + lNum;
		return random;
	}

	private static int[] randomizeAnswers() {
		/* 코드 작성(수정 가능) */
		final int COUNT_ANSWER_NUMBER = 9;
		int[] boardNumbers = new int[COUNT_ANSWER_NUMBER];

		HashSet<Integer> set = new HashSet<>();
		
		while (set.size() < 9) {
			int randomize = randomize(1, 9)*randomize(1, 9);
			if (randomize != resultNumber) {
					set.add(randomize);
			} 
		}
		
		Iterator<Integer> it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			
			boardNumbers[i] = (int) it.next();
			i++;

		}
		
		return boardNumbers;
	}
}
