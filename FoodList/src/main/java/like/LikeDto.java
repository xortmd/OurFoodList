package like;

import java.sql.Timestamp;

public class LikeDto {
	private String user_id;
	private int resto_code;
	private int like_code;
	private Timestamp reg_date;
	
	
	public LikeDto(String user_id, int resto_code, int like_code, Timestamp reg_date) {
		super();
		this.user_id = user_id;
		this.resto_code = resto_code;
		this.like_code = like_code;
		this.reg_date = reg_date;
	}
	
	public LikeDto(String user_id, int resto_code) {
		super();
		this.user_id = user_id;
		this.resto_code = resto_code;
	}
	
	public String getUser_id() {
		return user_id;
	}


	public int getResto_code() {
		return resto_code;
	}


	public int getLike_code() {
		return like_code;
	}


	public Timestamp getReg_date() {
		return reg_date;
	}


//	public void setUser_id(String user_id) {
//		this.user_id = user_id;
//	}


//	public void setResto_code(String resto_code) {
//		this.resto_code = resto_code;
//	}


//	public void setLike_code(String like_code) {
//		this.like_code = like_code;
//	}


//	public void setReg_date(Timestamp reg_date) {
//		this.reg_date = reg_date;
//	}
	
	

}
