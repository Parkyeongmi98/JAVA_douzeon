package collection;

import java.util.Stack;

public class StackTest {
	public static void main(String[] args) {
		Stack<String> s = new Stack<>();
		
		s.push("둘리");
		s.push("마이콜");
		s.push("도우너");
		
		while(!s.isEmpty()) {
			String str = s.pop();
			System.out.println(str);
		} // push된거 다 꺼내기
		
		//s.pop(); 비어 있는 경우에는 예외 발생 -> 에러
 
		s.push("둘리");
		s.push("마이콜");
		s.push("도우너");
		
		System.out.println(s.pop()); // 도우너 꺼내서 출력(후입선출)
		System.out.println(s.peek()); // 꺼내는게 아니라 보여주는거(마이콜 미리보기)
		System.out.println(s.pop()); // 마이콜 꺼내서 출력
		
	}
}
