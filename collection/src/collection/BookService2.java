package collection;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookService2 {

 //필드 //입력받으니까스캐너필요
 private Scanner sc = new Scanner(System.in);
 
 //원래도서를 저장할 목록 List
 private List<Book> books = new ArrayList<Book>();
 
 //기본생성자
 public BookService2() {
 books.add(new Book("우울해서 빵을 샀어", "안드레아 카스프르작", 16020, "이든서재", 1111));
 books.add(new Book("이중 하나는 거짓말", "김애란", 14400, "문학동네", 2222));
 books.add(new Book("빛이 이끄는 곳으로", "백희성", 16920, "북로망스", 3333));
 
}
  
 //메서드
 public void menu() {
 
 try{
 
 int menuNum= 0;
 
 do{
	 System.out.println("===도서 목록 프로그램===");
	 System.out.println("1. 도서 등록");
	 System.out.println("2. 도서 조회");
	 System.out.println("3. 도서 수정");
	 System.out.println("4. 도서 삭제");
	 System.out.println("5. 추천도서 뽑기");
	 System.out.println("0. 프로그램 종료");

	 System.out.print("메뉴를 입력하세요 : ");
 menuNum = sc.nextInt();
 
 switch(menuNum) { //메뉴에넣을숫자
 case 1: System.out.println(addBook()); break;
 case 2: searchBook(books); break;
 case 3: System.out.println(editBook()); break;
 case 4: System.out.println(deleteBook()); break;
 case 5: pickBook(); break;
 case 0: System.out.println("종료되었습니다"); break;
 
 default: System.out.println("메뉴에 있는번호만 입력하세요"); break;
}
 
 
 }while(menuNum!=0);
 
 }catch(Exception e) {
System.out.println("예외 발생");
 e.printStackTrace();
}
}
 
 public String addBook() {
System.out.println("<도서 등록>");
 
System.out.print("도서 번호 : ");
 int bookNum = sc.nextInt();
 sc.nextLine(); // 입력버퍼에 남은 개행문자 제거용
 
System.out.print("도서 제목 : ");
String title = sc.nextLine();
 
System.out.print("도서 저자 : ");
String writer = sc.nextLine();
 
System.out.print("도서 가격 : ");
 int price = sc.nextInt();
 sc.nextLine(); // 입력버퍼에 남은 개행문자 제거용
 
System.out.print("도서 출판사 : ");
String publisher = sc.nextLine();
 
 Book newBook = new Book(title, writer, price, publisher, bookNum);
 
 books.add(newBook);
 
 return "등록 완료";
}
 
 
 public void searchBook(List<Book> list) {
 
 if(list.isEmpty()) { //만약 list에 아무것도 없다면
System.out.println("등록된 도서가 없습니다. 등록해주세요"); 
 } else{
 for(Book temp : list) { 
System.out.println(temp);
}
}

}
 
 public String editBook() {
 searchBook(books); //등록된도서출력
 
System.out.println("<도서 수정>");
 
 int editMenu= 0; //수정 메뉴 선택용 변수
 
System.out.println("수정할 도서 번호를 입력하세요");
 int bookNum = sc.nextInt();
 
 boolean flag = true; //위의 코드는 랜덤한 수가 배열에 존재하는지 확인하는 코드입니다.
 //flag는 True와 False를 확인해야하기 때문에 boolean 타입으로 선언하고 
 //처음에는 true로 선언하였습니다.

 
 for(Book temp : books) {
 
 if(temp.getBookNum() == bookNum) {
 // 입력한 도서번호와 도서 목록의 도서 번호가 일치하는걸 찾았을때
 
 flag = false;
 
System.out.println("1. 도서명");
System.out.println("2. 도서 저자");
System.out.println("3. 도서 가격");
System.out.println("4. 도서 출판사");
System.out.println("0. 수정 종료");
System.out.print("어떤 정보를 수정하시겠습니까? ");
 
 editMenu = sc.nextInt();
 sc.nextLine(); // 입력버퍼에 남은 개행문자 제거
 
 switch(editMenu) {
 case 1 : 
   System.out.println("===도서명 수정===");
   System.out.print("수정할 도서명을 입력하세요 : ");
String title = sc.nextLine();
 temp.setTitle(title); break;

 case 2 :
  System.out.println("===도서 저자 수정===");
  System.out.print("수정할 저자명을 입력하세요 : ");
String writer = sc.nextLine();
 temp.setWriter(writer); break;
 
 case 3 :
  System.out.println("===도서 가격 수정===");
  System.out.print("수정할 가격을 입력하세요 : ");
 int price = sc.nextInt();
 temp.setPrice(price); break;
 
 case 4 :
  System.out.println("===도서 출판사 수정===");
  System.out.print("수정할 출판사를 입력하세요 : ");
String publisher = sc.nextLine();
 temp.setPublisher(publisher); break;
 
 case 0 : System.out.println("종료합니다..."); break;
 
 default: System.out.println("메뉴에 있는 번호만 선택하세요"); break;
}
 
}
}
 if(flag) {//false
 return "일치하는 도서 번호가 없습니다.";
}
 return "수정 완료";
}
 
 public String deleteBook() {
System.out.println("<도서 삭제>");
 
searchBook(books);
 
System.out.print("삭제할 도서의 번호를 입력하세요 : ");
 int deleteNum = sc.nextInt();
 
 for(Book temp : books) {
 
 if(temp.getBookNum() == deleteNum) {
 int index = books.indexOf(temp);
 // intList.indexOf(Object) : List에 일치하는 객체가 있으면 그 객체가 있는 index번호 반환
 
System.out.println("index 번호 : " + index);
System.out.print("정말 삭제하시겠습니까? (Y/N) : ");
 
 char answer = sc.next().toUpperCase().charAt(0); // "y" -> "Y" -> 'Y' 
 //toUpperCase(): 문자열을 대문자로 변환해서 반환하는 함수
 //문자형=char
 
 if(answer== 'Y') {
 books.remove(index); break;
 
 } else{
 return "삭제를 진행하지 않습니다";
}
}
 
}
 return "삭제 끝";
}
 
 
 //추천도서뽑기
 public void pickBook() {
 
 int max = books.size();
 
 int random= (int)(Math.random() * max); 
 //랜덤함수는 기본형이 Double형이기에 (int)로 정수화 시켜주어야한다.
 // 0 ~ 리스트의 마지막인덱스번호
 
System.out.println(books.get(random).getTitle());
}


 
 
}