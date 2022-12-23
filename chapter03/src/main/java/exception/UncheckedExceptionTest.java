package exception;

public class UncheckedExceptionTest {

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		
		// 에러 : 배열길이가 5인데 for문을 5번 반복을 하면 0부터 시작되서 1이 부족함.
		for(int i = 0; i <= a.length; i++) {
			System.out.println(a[i]);
		}
	}

}
