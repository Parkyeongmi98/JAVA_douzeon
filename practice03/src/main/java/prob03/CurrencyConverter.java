package prob03;

public class CurrencyConverter {
	private static double rate;
	
	public static double toDollar(double won) {
		double ko = won/rate;
		return ko;
	}
	
	public static double toKRW(double dollar) { 
		double us = dollar * rate;
		return us;
	}
	
	public static void setRate(double r) {
		rate = r;
	}

}
