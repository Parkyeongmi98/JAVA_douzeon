package prob04;

public class Person {
	private static int numberOfPerson; // 전체 인구수
	/* 코드 작성 */
	private int age = 12;
	private String name = "";
	
	public static int getPopulation() {
		int result = numberOfPerson += 1;
		return result;
	}
	public static void setNumberOfPerson(int numberOfPerson) {
		Person.numberOfPerson = numberOfPerson;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Person() {
		
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public void selfIntroduce() {
		System.out.println("내 이름은 " + this.name + "이며, 나이는 " + this.age + "입니다.");
	}
	
}
