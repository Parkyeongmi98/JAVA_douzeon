package thread;

public class AlphabetThread extends Thread {

	@Override
	public void run() {
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
