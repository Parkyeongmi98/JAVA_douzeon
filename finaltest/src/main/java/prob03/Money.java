package prob03;

public class Money {
	private int amount;
	
	/* 코드 작성 */
	public Money(int amount) {
		this.amount = amount;
	}

	public Money add(Money money) {
		int result = this.amount + money.amount;
		Money m = new Money(result);
		return m;
	}
	
	public Money minus(Money money) {
		int result = this.amount - money.amount;
		Money m = new Money(result);
		return m;
	}
	
	public Money multiply(Money money) {
		int result = this.amount * money.amount;
		Money m = new Money(result);
		return m;
	}
	
	public Money devide(Money money) {
		int result = this.amount / money.amount;
		Money m = new Money(result);
		return m;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Money && this.amount == ((Money)obj).amount) {
			return true;	
		}
		return false;
	}
}
