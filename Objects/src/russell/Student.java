package russell;

import java.util.Calendar;

public class Student implements Comparable <Student>{
	private String firstName, lastName, address, city, postalCode, phoneNumber, birthday;
	private Province province;
	private long studentNumber;
	public static long idGenerator = 300000000;
	public Student(){
		setStudentNumber(idGenerator);
		idGenerator++;
	}
	public Student(long iStudentNumber){
		setFirstName("nothing");
		setStudentNumber(iStudentNumber);
	}
	public Student(String iFirstName, String iLastName){
		setFirstName(iFirstName);
		setLastName(iLastName);
		setStudentNumber(idGenerator);
		idGenerator++;
	}
	public Student(String iFirstName, String iLastName, long iStudentNumber){
		setFirstName(iFirstName);
		setLastName(iLastName);
		setAddress("nothing");
		setStudentNumber(iStudentNumber);
	}
	public Student(String iFirstName, String iLastName, String iAddress, String iCity, String iProvince, String iPostalCode, String iPhoneNumber, String iBirthday){
		setFirstName(iFirstName);
		setLastName(iLastName);
		setAddress(iAddress);
		setCity(iCity);
		setProvince(iProvince);
		setPostalCode(iPostalCode);
		setPhoneNumber(iPhoneNumber);
		setBirthday(iBirthday);
		setStudentNumber(idGenerator);
		idGenerator++;
	}
	public Student(String iFirstName, String iLastName, String iAddress, String iCity, String iProvince, String iPostalCode, String iPhoneNumber, String iBirthday, long iStudentNumber){
		setFirstName(iFirstName);
		setLastName(iLastName);
		setAddress(iAddress);
		setCity(iCity);
		setProvince(iProvince);
		setPostalCode(iPostalCode);
		setPhoneNumber(iPhoneNumber);
		setBirthday(iBirthday);
		setStudentNumber(iStudentNumber);
	}
	public void setFirstName(String name){
		this.firstName = name;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public void setLastName(String name){
		this.lastName = name;
	}
	public String getLastName(){
		return this.lastName;
	}
	public static boolean trySetStreetNum(String address){
		char[] charStreetNum = address.split(" ")[0].toCharArray();
		for(int i=0; i< charStreetNum.length; i++){
			if(!Character.isDigit(charStreetNum[i])){
				System.out.println("Please enter a valid street number");
				return false;
			}
		}
		return true;
	}
	public void setAddress(String address){
		address = address.replace(".", "");
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getCity(){
		return this.city;
	}
	public static boolean trySetProvince(String province){
		if(parseProvince(province)==null){
			return false;
		}
		return true;
	}
	public void setProvince(String province){
		this.province = parseProvince(province);
	}
	public Province getProvince(){
		return this.province;
	}
	public static boolean trySetPostalCode(String code){
		if(code.length()<7||code.length()>7){
			System.out.println("Please enter a valid postal code (X1X 1X1)");
			return false;
		}
		if(code.charAt(3)!=' '||!Character.isLetter(code.charAt(0))||!Character.isLetter(code.charAt(2))||!Character.isLetter(code.charAt(5))||!Character.isDigit(code.charAt(1))||!Character.isDigit(code.charAt(4))||!Character.isDigit(code.charAt(6))){
			System.out.println("Please enter a valid postal code (X1X 1X1)");
			return false;
		}
		return true;
	}
	public void setPostalCode(String code){
		code = code.toUpperCase();
		this.postalCode = code;
	}
	public String getPostalCode(){
		return this.postalCode;
	}
	public static boolean trySetPhoneNumber(String phoneNum){
		if(phoneNum.length()<12||phoneNum.length()>12){
			System.out.println("Please enter a valid phone number (111-111-1111)");
			return false;
		}
		if(phoneNum.charAt(3)!='-'||phoneNum.charAt(7)!='-'||!Character.isDigit(phoneNum.charAt(0))||!Character.isDigit(phoneNum.charAt(1))||!Character.isDigit(phoneNum.charAt(2))||!Character.isDigit(phoneNum.charAt(4))||!Character.isDigit(phoneNum.charAt(5))||!Character.isDigit(phoneNum.charAt(6))||!Character.isDigit(phoneNum.charAt(8))||!Character.isDigit(phoneNum.charAt(9))||!Character.isDigit(phoneNum.charAt(10))||!Character.isDigit(phoneNum.charAt(11))){
			System.out.println("Please enter a valid phone number (111-111-1111)");
			return false;
		}
		return true;
	}
	public void setPhoneNumber(String phoneNum){
		this.phoneNumber = phoneNum;
	}
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	public static boolean trySetBirthday(String date){
		if(date.length()<10||date.length()>10){
			System.out.println("Please enter a valid date (YYYY/MM/DD)");
			return false;
		}
		if(date.charAt(4)!='/'||date.charAt(7)!='/'||!Character.isDigit(date.charAt(0))||!Character.isDigit(date.charAt(1))||!Character.isDigit(date.charAt(2))||!Character.isDigit(date.charAt(3))||!Character.isDigit(date.charAt(5))||!Character.isDigit(date.charAt(6))||!Character.isDigit(date.charAt(8))||!Character.isDigit(date.charAt(9))){
			System.out.println("Please enter a valid date (YYYY/MM/DD)");
			return false;
		}
		if(Integer.parseInt(date.split("/")[0])>Calendar.getInstance().get(Calendar.YEAR)){
			System.out.println("Please enter a valid date (YYYY/MM/DD)");
			return false;
		}
		if(Integer.parseInt(date.split("/")[1])<13&&Integer.parseInt(date.split("/")[1])>0){
			if(Integer.parseInt(date.split("/")[1])==1||Integer.parseInt(date.split("/")[1])==3||Integer.parseInt(date.split("/")[1])==5||Integer.parseInt(date.split("/")[1])==7||Integer.parseInt(date.split("/")[1])==8||Integer.parseInt(date.split("/")[1])==10||Integer.parseInt(date.split("/")[1])==12){
				if(Integer.parseInt(date.split("/")[2])<0||Integer.parseInt(date.split("/")[2])>31){
					System.out.println("Please enter a valid date (YYYY/MM/DD)"); 
					return false;
				}
			}
			else if(Integer.parseInt(date.split("/")[1])==2){
				if(java.time.Year.of(Integer.parseInt(date.split("/")[0])).isLeap()){
					if(Integer.parseInt(date.split("/")[2])<0||Integer.parseInt(date.split("/")[2])>29){
						System.out.println("Please enter a valid date (YYYY/MM/DD)");
						return false;
					}
				}
				else{
					if(Integer.parseInt(date.split("/")[2])<0||Integer.parseInt(date.split("/")[2])>28){
						System.out.println("Please enter a valid date (YYYY/MM/DD)");
						return false;
					}
				}
			}
			else{
				if(Integer.parseInt(date.split("/")[2])<0||Integer.parseInt(date.split("/")[2])>30){
					System.out.println("Please enter a valid date (YYYY/MM/DD)"); 
					return false;
				}
			} 
		}
		else{
			System.out.println("Please enter a valid date (YYYY/MM/DD)"); 
			return false;
		}
		return true;
	}
	public void setBirthday(String date){
		this.birthday = date;
	}
	public String getBirthday(){
		return this.birthday;
	}
	public void setStudentNumber(long studentNum){
		this.studentNumber = studentNum;
	}
	public long getStudentNumber(){
		return this.studentNumber;
	}
	public boolean equals(Student s){
		return this.getStudentNumber() == s.getStudentNumber();
	}
	public String toString(){
		return getFirstName() + "/" + getLastName() + "/" + getAddress() + "/" + getCity() + "/" + getProvince() + "/" + getPostalCode() + "/" + getPhoneNumber() + "/" + getBirthday() + "/" + getStudentNumber();
	}
	public int compareTo(Student arg0) {
		if(this.getLastName().compareTo(arg0.getLastName())==0){
			return this.getFirstName().compareTo(arg0.getFirstName());
		}
		return this.getLastName().compareTo(arg0.getLastName());
	}
	public static Province parseProvince(String string){
		string = string.toLowerCase();
		switch(string){
			case"bc":
			case"british columbia":
				return Province.BC;
			case"ab":
			case"alberta":
				return Province.AB;
			case"sk":
			case"saskatchewan":
				return Province.SK;
			case"mb":
			case"manitoba":
				return Province.MB;
			case"on":
			case"ontario":
				return Province.BC;
			case"qc":
			case"quebec":
				return Province.AB;
			case"nl":
			case"newfoundland and labrador":
			case"newfoundland":
			case"labrador":
				return Province.NL;
			case"ns":
			case"nova scotia":
				return Province.NS;
			case"pe":
			case"pei":
			case"prince edward island":
				return Province.PE;
			case"nb":
			case"new brunswick":
				return Province.NB;
			case"yt":
			case"yukon":
			case"yukon territories":
				return Province.YT;
			case"nt":
			case"nwt":
			case"northwest territories":
				return Province.NT;
			case"nu":
			case"nunavut":
				return Province.NU;
			default:
				return null;
		}
	}
}