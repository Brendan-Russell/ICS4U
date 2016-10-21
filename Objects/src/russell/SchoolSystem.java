package russell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
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
			input = scan.nextLine();
			while(!Student.trySetProvince(input)){
				input = scan.nextLine();
			}
			province = input;
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
		if(student.getFirstName()==null||student.getFirstName().equals("nothing")){
			System.out.println("This student has no information");
			System.out.println("Student Number: " + student.getStudentNumber() + "\n");
		}
		else if(student.getAddress()==null||student.getAddress().equals("nothing")){
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
	public static void load(){
		try{
			String fileName = "src/russell/Students.txt";
			String fNumStudents = "src/russell/NumStudents.txt";
			String input = "";
			File file = new File(fileName);
			BufferedReader fbr = new BufferedReader(new FileReader(fileName));
			File stuFile = new File(fNumStudents);
			BufferedReader sfbr = new BufferedReader(new FileReader(fNumStudents));
			if(!file.exists()){
				file.createNewFile();
			}
			if(!stuFile.exists()){
				stuFile.createNewFile();
			}
			input = sfbr.readLine();
			if(Integer.parseInt(input)!=0){
				int numStudents = Integer.parseInt(input);
				String[] studentInfo = new String[numStudents];
				if(numStudents!=0){
					for(int i=0; i<numStudents; i++){
						studentInfo[i] = fbr.readLine();
					}
					Student.idGenerator = Integer.parseInt(studentInfo[studentInfo.length-1].split("/")[8])+1;
					for(int i=0; i<numStudents; i++){
						if(studentInfo[i].split("/")[0].equals("nothing")){
							studRecs.add(new Student(Long.parseLong(studentInfo[i].split("/")[8])));
						}
						else if(studentInfo[i].split("/")[2].equals("nothing")){
							studRecs.add(new Student(studentInfo[i].split("/")[0], studentInfo[i].split("/")[1], Long.parseLong(studentInfo[i].split("/")[8])));
						}
						else{
							studRecs.add(new Student(studentInfo[i].split("/")[0], studentInfo[i].split("/")[1], studentInfo[i].split("/")[2], studentInfo[i].split("/")[3], studentInfo[i].split("/")[4], studentInfo[i].split("/")[5], studentInfo[i].split("/")[6], studentInfo[i].split("/")[7],  Long.parseLong(studentInfo[i].split("/")[8])));
						}
					}
				}
			}
			fbr.close();
			sfbr.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void save(){
		try{
			String fileName = "src/russell/Students.txt";
			String fNumStudents = "src/russell/NumStudents.txt";
			File file = new File(fileName);
			File stuFile = new File(fNumStudents);
			if(!file.exists()){
				file.createNewFile();
			}
			if(!stuFile.exists()){
				stuFile.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(fileName);
			PrintStream fps = new PrintStream(fos);
			FileOutputStream sfos = new FileOutputStream(fNumStudents);
			PrintStream sfps = new PrintStream(sfos);
			for(int i=0; i<studRecs.size(); i++){
				if(studRecs.get(i).getFirstName()==null){
					studRecs.get(i).setFirstName("nothing");
				}
				if(studRecs.get(i).getAddress()==null){
					studRecs.get(i).setAddress("nothing");
				}
				fps.println(studRecs.get(i).toString());
			}
			sfps.println(studRecs.size());
			sfps.close();
			fps.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){		
		String input;
		load();
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
					input = "1";
				}
				else if(studRecs.size()>1){
					System.out.println("Which student do you want to print? (Enter their student number)");
					String tryInput = scan.nextLine();
					while(!Character.isDigit(tryInput.toCharArray()[0])){
						System.out.println("Please enter a number");
						tryInput = scan.nextLine();
					}
					long stuNum = Long.parseLong(tryInput);
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
					input = "1";
				}
				else if(studRecs.size()>1){
					System.out.println("Which student do you want to delete? (Enter their student number)");
					String tryInput = scan.nextLine();
					while(!Character.isDigit(tryInput.toCharArray()[0])){
						System.out.println("Please enter a number");
						tryInput = scan.nextLine();
					}
					long stuNum = Long.parseLong(tryInput);
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
			else if(input.equals("6")){
				save();
			}	
			if(input.equals("2")||input.equals("3")||input.equals("4")){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}while(!input.equals("10"));
		save();
		quit();
		scan.close();
	}
}