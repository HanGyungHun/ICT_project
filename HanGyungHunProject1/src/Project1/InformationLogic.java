package Project1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;





public class InformationLogic extends Thread{
public static final int MAX_Persons =10;
	Map<Character,List<NameFindList>> ListInformation = new HashMap<>();
	List<NameFindList> nameList;
	
	
	
	public void BasicMianMenu() {/////////출력용 메소드
		System.out.println("================주소록 메뉴=================");
		System.out.println("1.입력  2.출력  3.수정  4.삭제  5.검색  9.종료");
		System.out.println("==========================================");
		System.out.println("메뉴를 입력하세요??");
		
		
	}////////////// BasicMianMenu
	private void FindSupMenu(int SupMenu) {/////입력용 sup메뉴
		if(ListInformation.size()== MAX_Persons) {
			System.out.println("인원이 다 찼어요 ..인원 삭제후 다시 시도해 주세요");
			return;
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("(본인)이름을 입력하세요.");
		String name= sc.nextLine().trim();
		char consonant = getInitialConsonant(name);
		if(consonant=='0') {
			System.out.println("한글명이 아닙니다");
			return;
		}
		System.out.println("나이를 입력하세요.");
		int age = getAges(sc);
		System.out.println("사는곳을 입력하세요.");
		String addr= sc.nextLine().trim();
		System.out.println("연락처를 입력하세요.(-)제외");
		int phnum = getphnum(sc);
		
		if(!ListInformation.containsKey(consonant)) {//키값이 없으면 객체 생성
			nameList = new Vector<>();
		}
		else {//키값 존재 하면 저장된 값 출력
			nameList=ListInformation.get(consonant);
		}
		
		nameList.add(new NameFindList(name, age, addr, phnum));
		
		ListInformation.put(consonant, nameList);
	}//////////////////FindSupMenu
	
	
	private int getphnum(Scanner sc1) {/////연락처 문자 방지
		int ph;
		while(true) {
			try {
				ph=Integer.parseInt(sc1.nextLine().trim());
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("연락처는 숫자만 입력해 주세요.");
			}
		}
		return ph;
	}//////////////getphnum
	
	
	

	private int getAges(Scanner sc) {//나이 문자입력 방지용
		int years;
		while(true) {
			try {
				years=Integer.parseInt(sc.nextLine().trim());
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("나이는 숫자만...");
			}
		}
		return years;
	}//////////////getAges1
	public int getMenuAndSup() {//메뉴 번호 (외) 다른 문자나 숫자 방지
		Scanner sc = new Scanner(System.in);
		String menuSup;
		while(true) {
			menuSup = sc.nextLine().trim();
			if(!Prevent(menuSup)) {
				System.out.println("메뉴 번호는 숫자만 가능합니다.");
				continue;
			}
			break;
		}
		return Integer.parseInt(menuSup);
		
	}//////////getMenuAndSup
	
	public static boolean Prevent(String value) {//문자열이 숫자 형식이면 반환함
		for(int i=0; i < value.length() ;i++) {
		int codeAt = Character.codePointAt(value,i);
		if(!(codeAt >='0' && codeAt<='9')) return false;
		}
		return true;	
	}///////////////////Prevent
	
	
	
	public void MenuBranch(int MainMnue) {///분기용 메소드
		switch(MainMnue) {
		case 1://입력
			while(true) {
			printSub();
			int menuSup = getMenuAndSup();
			if (menuSup == 2) break;
			switch(menuSup) {
			case 1:
				FindSupMenu(menuSup); break;
			case 2:
				default:System.out.println("메뉴에 없는 번호입니다. 다시 입력하세요");
				}///switch
			}/////////////while
			
			break;
			
		case 2://출력
			ConsonantSubMeun();
			int menuSup1 = getMenuAndSup();
			if (menuSup1 == 2) break;
			switch(menuSup1) {
			case 1:
				printConsonant(menuSup1); break;
			case 2:
				default:System.out.println("메뉴에 없는 번호입니다. 다시 입력하세요");
			}///////////switch
			
			break;
			
		case 3://수정
			 update();
			break;
			
		case 4://삭제
			delete();
			break;
			
		case 5://검색
			findPersonByName();
			break;
			
			
		case 9://종료
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
			break;
			
		default:
			System.out.println("메뉴에 없는 번호입니다\r 다시 입력해 주세요.");
				
		}
	}

	
	private void printSub() {////입력용 sub메뉴
		System.out.println("---------------------");
		System.out.println("1.정보 입력  2.메인 메뉴");
		System.out.println("---------------------");
		System.out.println("번호를 선택하세요?");
	}/////////////printSub
	
	private void ConsonantSubMeun() {///출력용 sub 메뉴
		System.out.println("--------------------");
		System.out.println("1.출력 하기  2.메인메뉴 ");
		System.out.println("--------------------");
		System.out.println("번호를 선택하세요?");
		
	}//////////////ConsonantSubMeun
	
	private void printConsonant(int sbm) {//출력용 code
			Set<Character> keys = ListInformation.keySet();
			for(Character key:keys) {
				System.out.println(String.format("[%c로 시작하는 명단]",key));
				List<NameFindList> values= ListInformation.get(key);
				for(NameFindList value: values) 
					System.out.println(value.get()+"\r\n");
			}
			
			
	}///////printConsonant
	public static char getInitialConsonant(String value) {
		if(!Pattern.matches("^[가-힣]{2,}$", value.trim())) return '0';
		char lastName=value.charAt(0);
		
		int index = (lastName-'가')/28/21;//초성의 인덱스 얻기
		char[] initialConsonants= {'ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
		return initialConsonants[index];
	}///////////////
	
	

	private NameFindList search(String title) {
		System.out.println(title+"할 사람의 이름을 입력하세요?");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine().trim();
		char a=getInitialConsonant(name);
		List<NameFindList> list=ListInformation.get(a);
		for(NameFindList n: list) {
			if(n.name.equals(name))
				return n;
		}
		System.out.println(name+"(으로) 검색된 정보가 없어요");
		return null;
	}//////////searchPerson()
	
	private void findPersonByName() {
		NameFindList findPerson=search("검색");
		if(findPerson !=null) {
			System.out.println(String.format("[%s로 검색한 결과]", findPerson.name));
			findPerson.print();
		}
	}//////////////findPersonByName()
	
	private void update() {
		
		NameFindList list=search("수정");
		
		
		if(list !=null) {
			Scanner sc = new Scanner(System.in);
			
			//나이 수정
			System.out.printf("====(현재 %s살)수정할 나이를 입력하세요!!====%n",list.age);
			list.age = getAges(sc);
			System.out.printf("====(현재 주소 %s번지)수정할 주소를 입력하세요!!====%n",list.addr);
			list.addr = sc.nextLine().trim();
			System.out.printf("====(현재 번호 %s)수정할 번호를 입력하세요!!====%n",list.phnum);
			list.phnum = getphnum(sc);
			
			System.out.printf("[%s가(이) 아래와 같이 수정되었습니다]%n",list.name);
			list.print();//수정 내용을 확인하기 위한 출력
			
		}
	}
	
	private void delete() {
		NameFindList list1=search("삭제");
		Set<Character> keys = ListInformation.keySet();
		if(list1 !=null) {
			
			
			for(Character key: keys) {
				if(key.equals(key)) {
					ListInformation.remove(key);
					System.out.println(String.format("[%s가 삭제되었습니다]",list1.name));
					break;
				}
		}
	}
		}
	
	public void main(String[] args) {/////////로딩/
		InformationLogic thread= new InformationLogic();
		thread.start();
		try {
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) {e.printStackTrace();}
		if(thread.isAlive())
			thread.interrupt();
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void run() {//////로딩/
		System.out.println("5초 로딩");
		String i="♬~♪~";
		try {
			while(true) {
				System.out.print(i);
				sleep(1000);
			}
		}
		catch (InterruptedException e) {System.out.println("\r***로딩 완료***");}
	}
	
}///////class

	
	
	
	
	







