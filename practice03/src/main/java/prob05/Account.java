package prob05;

public class Account {
	private String accountNo;
	private int balance;
	
	public Account (String accountNo) {
		this.accountNo = accountNo;
		System.out.println(this.accountNo + " 계좌가 개설되었습니다.");
	}
	
	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void save (int in) {
		if (in > 0 ) {
			setBalance(balance + in);
			System.out.println(this.accountNo + "계좌에 " + in + "만원이 입금되었습니다.");
		} else {
			System.out.println("값을 다시 입력해주세요.");
		}
	}
	
	public void deposit (int out) {
		if (out <= balance) {
			setBalance(balance - out);
		System.out.println(this.accountNo + "계좌에 " + out + "만원이 출금되었습니다.");
		} else {
			System.out.println("출금액을 초과하였습니다.");
		}
	}

}
