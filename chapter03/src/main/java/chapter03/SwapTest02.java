package chapter03;

public class SwapTest02 {

	public static void main(String[] args) {
		int a = 10; // 지역변수(블럭{} 안에 있는 변수)
		int b = 20; // 지역변수
		
		System.out.println("a: " + a + ", b: " + b);
		
		swap(a, b);
		
		System.out.println("a: " + a + ", b: " + b);
	}
	
	public static void swap(int m, int n) {
		int temp = m;
		m = n;
		n = temp;
	}

}
