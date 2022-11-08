package review;

import java.sql.Timestamp;

public class ReviewDto {
	private int no;
	private int resto_code;
	private String coment;
	private String user_id;
	private Timestamp reg_date;
	private double give_grade;
	
	
	public ReviewDto(int no, int resto_code, String coment, String user_id, Timestamp reg_date, double give_grade) {
		super();
		this.no = no;
		this.resto_code = resto_code;
		this.coment = coment;
		this.user_id = user_id;
		this.reg_date = reg_date;
		this.give_grade = give_grade;
	}
	
	public ReviewDto(int resto_code, String coment, String user_id, double give_grade) {
		super();
		this.resto_code = resto_code;
		this.coment = coment;
		this.user_id = user_id;
		this.give_grade = give_grade;
	}


	public int getNo() {
		return no;
	}


	public int getResto_code() {
		return resto_code;
	}


	public String getComent() {
		return coment;
	}


	public String getUser_id() {
		return user_id;
	}


	public Timestamp getReg_date() {
		return reg_date;
	}


	public double getGive_grade() {
		return give_grade;
	}


//	public void setNo(int no) {
//		this.no = no;
//	} d


//	public void setResto_code(int resto_code) {
//		this.resto_code = resto_code;
//	}


	public void setComent(String coment) {
		this.coment = coment;
	}


//	public void setUser_id(String user_id) {
//		this.user_id = user_id;
//	}


//	public void setReg_date(Timestamp reg_date) {
//		this.reg_date = reg_date;
//	}


	public void setGive_grade(double give_grade) {
		this.give_grade = give_grade;
	}
	
	
	
	
	

}
