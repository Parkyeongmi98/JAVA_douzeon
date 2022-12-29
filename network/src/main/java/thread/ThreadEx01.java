package thread;

public class ThreadEx01 {
	public static void main(String[] args) {
//		for(int i = 0; i < 10; i++) {
//			System.out.print(i);
//		}
		
		new DigitThread().start(); // Thread 실행
		
		for(char c = 'a'; c <= 'z'; c++) {
			System.out.print(c);
			
			try {
				Thread.sleep(1000); // 1초 재우기(1초씩 출력)
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
}
