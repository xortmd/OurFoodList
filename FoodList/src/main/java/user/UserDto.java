package user;
// 이윤정

import java.sql.Timestamp;

public class UserDto {
	private String id;
	private String password;
	private String name;
	private String phone;
	private Timestamp birth;
	private Timestamp reg_date;
	
	// 생성자
	public UserDto(String id, String password, String name, String phone, Timestamp birth, Timestamp reg_date) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.reg_date = reg_date;
	}
	
	// getter & setter	
	public String getId() {
		return id;
	}

//	public void setId(String id) {
//		this.id = id;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getBirth() {
		return birth;
	}

//	public void setBirth(Timestamp birth) {
//		this.birth = birth;
//	}

	public Timestamp getReg_date() {
		return reg_date;
	}

//	public void setReg_date(Timestamp reg_date) {
//		this.reg_date = reg_date;
//	}
	
	
	
	
}
