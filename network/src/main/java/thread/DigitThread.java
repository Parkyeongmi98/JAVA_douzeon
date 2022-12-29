package thread;

public class DigitThread extends Thread {
	// Thread를 상속받아 코드 싣기
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.print(i);
			
			try {
				Thread.sleep(1000); // 1초 재우기(1초씩 출력)
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
}
