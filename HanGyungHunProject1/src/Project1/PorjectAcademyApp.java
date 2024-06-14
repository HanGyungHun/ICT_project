package Project1;

public class PorjectAcademyApp {
	
	
	
	public static void main(String[] args) {
		InformationLogic loding = new InformationLogic();
		loding.main(args);
		InformationLogic logic = new InformationLogic();
		
		while(true) {
			logic.BasicMianMenu();
			int Main=logic.getMenuAndSup();
			logic.MenuBranch(Main);
		}///////////while
	}//////main

}/////class
