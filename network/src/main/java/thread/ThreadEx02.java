package thread;

public class ThreadEx02 {
	public static void main(String[] args) {
		Thread thread01 = new DigitThread(); // Thread 객체 생성
		Thread thread02 = new AlphabetThread(); 
		
		thread01.start(); // Thread 실행
		thread02.start(); 
	}
}
