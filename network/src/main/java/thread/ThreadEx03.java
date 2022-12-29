package thread;

public class ThreadEx03 {
	public static void main(String[] args) {
		Thread thread01 = new DigitThread(); // Thread 객체 생성
		Thread thread02 = new AlphabetThread(); 
		Thread thread03 = new Thread(new UpperCaseAlphabetRunnableImpl()); 

		
		thread01.start(); // Thread 실행
		thread02.start(); 
		thread03.start();
	}
}
