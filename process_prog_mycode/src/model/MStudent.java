package model;

public class MStudent extends Model{
	private String password;
	private String grade;
//	private int applicableCredit;
//	private int currentCredit;
	public MStudent() {
		
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

//	public int getApplicableCredit() {
//		return applicableCredit;
//	}
//
//	public void setApplicableCredit(int applicableCredit) {
//		this.applicableCredit = applicableCredit;
//	}
//
//	public int getCurrentCredit() {
//		return currentCredit;
//	}
//
//	public void setCurrentCredit(int currentCredit) {
//		this.currentCredit = currentCredit;
//	}

	
}
