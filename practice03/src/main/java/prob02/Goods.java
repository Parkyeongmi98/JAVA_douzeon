package prob02;

public class Goods {
	public String name;
	public int price;
	public int number;
	
	public Goods (String name, int price, int number) {
		this.name = name;
		this.price = price;
		this.number = number;
	}
	
	public void show () {
		System.out.println(this.name + " (가격 : " + this.price +"원)이 " + this.number + "개 입고 되었습니다.");
	}
	
}
