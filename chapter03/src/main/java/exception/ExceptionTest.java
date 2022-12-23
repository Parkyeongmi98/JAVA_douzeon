package exception;

public class ExceptionTest {

	public static void main(String[] args) {
		int a = 12;
		int b = 10 - a;
		
		System.out.println("Come Code1...");
		try {
			System.out.println("Come Code2...");
			System.out.println("Come Code3...");
			
			int result = (1 + 2 + 3) / b;
			
			System.out.println("Come Code4...");
			System.out.println("Come Code5...");
		} catch(ArithmeticException ex) {
			/* 예외 처리 */
			// 1. 로깅
			System.out.println("error: " + ex);
			
			// 2. 사과
			System.out.println("미안합니다...");
			
			// 3. 정상종료
			// System.exit(0);
			return;
		} finally {
			System.out.println("자원 정리 예: file close...");
		}
		
		System.out.println("Come Code6...");
		System.out.println("Come Code7...");
		
	}

}
