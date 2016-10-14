package russell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SchoolSystem {
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Student> studRecs = new ArrayList<Student>();
	public static void addStudent(){
		String input, firstInput;
		String firstName = "", lastName = "", address, city, province, postalCode, phoneNumber, birthday;
		studRecs.trimToSize();
		System.out.println("What would you like to input?\n1. First Name, Last Name, Address, Postal Code, City, Province, Phone Number, Birthday\n2. First Name, Last Name\n3. No information");
		firstInput = scan.nextLine();
		while(!Character.isDigit(firstInput.toCharArray()[0])||Integer.parseInt(firstInput)<1||Integer.parseInt(firstInput)>3){
			System.out.println("Please enter either 1, 2, or 3.");
			firstInput = scan.nextLine();
		}
		if(!firstInput.equals("3")){
			System.out.println("What is the student's first name?");
			firstName = scan.nextLine();
			System.out.println("What is the student's last name?");
			lastName = scan.nextLine();
		}
		if(firstInput.equals("1")){
			System.out.println("What is the students address?");
			input = scan.nextLine();
			while(!Student.trySetStreetNum(input)){
				input = scan.nextLine();
			}
			address = input;
			System.out.println("What province does the student live in?");
			province = scan.nextLine();
			System.out.println("What city does the student live in?");
			city = scan.nextLine();
			System.out.println("What is the student's postal code? (X1X 1X1)");
			input = scan.nextLine();
			while(!Student.trySetPostalCode(input)){
				input = scan.nextLine();
			}
			postalCode = input;
			System.out.println("What is the student's phone number? (111-111-1111)");
			while(!Student.trySetPhoneNumber(input)){
				input = scan.nextLine();
			}
			phoneNumber = input;
			System.out.println("What is the student's birthday? (YYYY/MM/DD)");
			while(!Student.trySetBirthday(input)){
				input = scan.nextLine();
			}
			birthday = input;
			studRecs.add(new Student(firstName, lastName, address, city, province, postalCode, phoneNumber, birthday));
		}
		else if(firstInput.equals("2")){
			studRecs.add(new Student(firstName, lastName));
		}
		else{
			studRecs.add(new Student());
		}
	}
	public static void printStudent(Student student){
		if(student.getFirstName()==null){
			System.out.println("This student has no information");
			System.out.println("Student Number: " + student.getStudentNumber() + "\n");
		}
		else if(student.getAddress()==null){
			System.out.println("\nName: " + student.getFirstName() + " " + student.getLastName());
			System.out.println("Student Number: " + student.getStudentNumber() + "\n");
		}
		else{
			System.out.println("\nName: " + student.getFirstName() + " " + student.getLastName());
			System.out.println("Address: " + student.getAddress() + ", " + student.getCity() + ", " + student.getProvince() + ", " + student.getPostalCode());
			System.out.println("Phone Number: " + student.getPhoneNumber());
			System.out.println("Birthday: " + student.getBirthday());
			System.out.println("Student Number: " + student.getStudentNumber() + "\n");	
		}
	}
	public static void printAllStudents(){
		for(int i=0; i<studRecs.size(); i++){
			printStudent(studRecs.get(i));
		}
	}
	public static boolean trySearchStudents(long stuNumber){
		String stringNumber = Long.toString(stuNumber);
		if(stringNumber.length()<9||stringNumber.length()>9){
			System.out.println("Please enter a valid student number (9-Digit Number)");
			return false;
		}
		char[] charStuNum = stringNumber.toCharArray();
		for(int i=0; i<charStuNum.length; i++){
			if(!Character.isDigit(charStuNum[i])){
				System.out.println("Please enter a valid student number (9-Digit Number)");
				return false;
			}
		}
		for(int i=0; i<studRecs.size(); i++){
			if(studRecs.get(i).getStudentNumber()==stuNumber){
				return true;
			}
		}
		System.out.println("That student does not exist.");
		return false;
	}
	public static Student searchStudents(long stuNumber){
		for(int i=0; i<studRecs.size(); i++){
			if(studRecs.get(i).getStudentNumber()==stuNumber){
				return studRecs.get(i);
			}
		}
		return null;
	}
	public static void removeStudent(long stuNumber){
		for(int i=0; i<studRecs.size(); i++){
			if(studRecs.get(i).getStudentNumber()==stuNumber){
				studRecs.remove(i);
			}
		}
		studRecs.trimToSize();
	}
	
	public static void quit(){
		System.exit(0);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		String input;
		do{
			System.out.println("Choose one of the following options:\n1. Enter a new student\n2. Print out a student's information\n3. Print information of all students\n4. Delete a student\n5. Sort all students by last name\n10. Quit");
			input = scan.nextLine();
			try{
				Integer.parseInt(input);
			}catch(IllegalArgumentException e){
				System.out.println("Please enter a number from 1-10");
				main(args);
			}
			while(Integer.parseInt(input)<0||Integer.parseInt(input)>10){
				System.out.println("Please enter a number from 1-10\nChoose one of the following options:\n1. Enter a new student\n2. Print out a student's information\n3. Print information of all students\n4. Delete a student\n5. Sort all students by last name\n10. Quit");
				input = scan.nextLine();
			}
			if(input.equals("1")){
				addStudent();
			}
			else if(input.equals("2")){
				if(studRecs.size()==0){
					System.out.println("There are no students in the database");
				}
				else if(studRecs.size()>1){
					System.out.println("Which student do you want to print? (Enter their student number)");
					long stuNum = Long.parseLong(scan.nextLine());
					if(!trySearchStudents(stuNum)){
						main(args);
					}
					printStudent(searchStudents(stuNum));
				}
				else{
					printStudent(studRecs.get(0));
				}
			}
			else if(input.equals("3")){
				if(studRecs.size()==0){
					System.out.println("There are no students in the database");
				}
				else{
					printAllStudents();
				}
			}
			else if(input.equals("4")){
				if(studRecs.size()==0){
					System.out.println("There are no students in the database");
				}
				else if(studRecs.size()>1){
					System.out.println("Which student do you want to delete? (Enter their student number)");
					long stuNum = Long.parseLong(scan.nextLine());
					if(!trySearchStudents(stuNum)){
						main(args);
					}
					removeStudent(stuNum);
				}
				else{
					studRecs.remove(0);
				}
			}
			else if(input.equals("5")){
				Collections.sort(studRecs);
			}
			if(!input.equals("1")&&!input.equals("5")&&!input.equals("10")){
				Thread.sleep(5000);
			}
		}while(!input.equals("10"));
		quit();
		scan.close();
	}
}