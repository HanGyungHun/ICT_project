package Project1;

public class NameFindList extends InformationLogic{

	public String name;
	public int age;
	public String addr;
	public int phnum;
	

	public NameFindList(String name, int age, String addr, int phnum) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.phnum = phnum;
	}


	String get() {
		return String.format("->이름:%s%n->나이:%s%n->주소:%s%n->연락처:%s%n",name,age,addr,phnum);
	}
	void print() {
		System.out.println(get());
	}





	
	
	
}
