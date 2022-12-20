package prob03;

public class Prob03 {
	public static void printCharArray(char c[]) {
		System.out.println(new String(c, 0, c.length));
	}

	public static char[] replaceSpace(char c[]) {
		c = new String(c, 0, c.length).replaceAll(" ", ",").toCharArray();
		return c;
	}

	public static void main(String args[]) {
		char c[] = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 'p', 'e', 'n', 'c', 'i', 'l', '.' };

		// 원래 배열 원소 출력
		printCharArray(c);

		// 공백 문자 바꾸기
		c=replaceSpace(c);

		// 수정된 배열 원소 출력
		printCharArray(c);
	}

}
