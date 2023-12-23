package entity;

public class Guest {
	
	private int id;
	private String fname;
	private String lname;
	private String sex;
	private int age;
	private String phoneNum;
	private String email;
	private String adress;
	
	// Constructor
	
	public Guest(int id, String fname, String lname, String sex, int age, String phoneNum, String email, String adress) {
		
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.sex = sex;
		this.age = age;
		this.phoneNum = phoneNum;
		this.email = email;
		this.adress = adress;
	}
	
	// Getters - Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		
		return "Id: " + this.id + ", first name: " + this.fname + ", last name: " + this.lname + ", sex: " + this.sex + ", age: " + 
	
				this.age + ", phone number: " + this.phoneNum + ", email: " + this.email + ", adress: " + this.adress;
	}
	
	
	
	
	
	
	
}
