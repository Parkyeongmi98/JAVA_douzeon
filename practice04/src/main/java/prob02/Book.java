package prob02;

public class Book {
	private int bookNo;
	private String title;
	private String author;
	public int stateCode;
	
	// 생성자
	public Book (int bookNo, String title, String author) {
		this.stateCode = 1;
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
	}
	
	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	// 대여 메소드
	public void rent() {
		this.stateCode = 0;
		System.out.println(this.title + "이(가) 대여 됐습니다.");
	}
	
	// 출력 메소드
	public void print() {
//		for () {
//			
//		}
	}

}
