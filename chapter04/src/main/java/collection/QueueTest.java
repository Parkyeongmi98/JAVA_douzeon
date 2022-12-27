package collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
	public static void main(String[] args) {
		Queue<String> q = new LinkedList<>();
		
		q.offer("마이콜");
		q.offer("둘리");
		q.offer("도우너");
		
		while(!q.isEmpty()) {
			String s = q.poll();
			System.out.println(s);
		}
		
		q.offer("마이콜");
		q.offer("둘리");
		q.offer("도우너");
		
		System.out.println(q.poll()); // 마이콜 꺼내서 출력(선입선출)
		System.out.println(q.peek()); // 다음꺼 미리보기 출력
		System.out.println(q.poll()); // 둘리 꺼내서 출력
		System.out.println(q.poll());
		System.out.println(q.poll());
		
	}	
}
