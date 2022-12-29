package thread;

public class UpperCaseAlphabet {
	public void print() {
		for(char c = 'A'; c <= 'Z'; c++) {
			System.out.print(c);
			
			try {
				Thread.sleep(1000); // 1초 재우기(1초씩 출력) -> 쓰레드랑 관련 x
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
}
