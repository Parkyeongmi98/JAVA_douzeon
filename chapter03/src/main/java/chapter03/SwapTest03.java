package chapter03;

import mypackage.Value;

public class SwapTest03 {

	public static void main(String[] args) {
		Value a = new Value(10); // 지역변수(블럭{} 안에 있는 변수)
		Value b = new Value(20); // 지역변수
		
		System.out.println("a: " + a.val + ", b: " + b.val);
		
		swap(a, b);
		
		System.out.println("a: " + a.val + ", b: " + b.val);
	}
	
	public static void swap(Value m, Value n) {
		int temp = m.val; // 객체를 생성했으므로 heap 영역에 값을 저장하고
		m.val = n.val;    // stack에는 값의 레퍼런스를 참조한다.
		n.val = temp;
	}

}
