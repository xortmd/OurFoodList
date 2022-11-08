package restaurant;

import java.sql.Timestamp;

// 정택승
public class RestaurantDto {
	private int code;
	private String res_name;
	private String address;
	private String res_phone; // 유효성검사
	private String kind;
	private Timestamp reg_date;
	private Timestamp mod_date;
	private String image_url;
	private double ave_grade;
	private int like_cnt;
	private int review_cnt;
	
	public RestaurantDto(int code, String res_name, String address, String res_phone, String kind,
			Timestamp reg_date, Timestamp mod_date, String image_url,
			double ave_grade, int like_cnt, int review_cnt) {
		super();
		this.code = code;
		this.res_name = res_name;
		this.address = address;
		this.res_phone = res_phone;
		this.kind = kind;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
		this.image_url = image_url;
		this.ave_grade = ave_grade;
		this.like_cnt = like_cnt;
		this.review_cnt = review_cnt;
	}
	
	public RestaurantDto(int code, String res_name, String address, String res_phone, String kind,
			Timestamp reg_date, String image_url, double ave_grade, int like_cnt, int review_cnt) {
		super();
		this.code = code;
		this.res_name = res_name;
		this.address = address;
		this.res_phone = res_phone;
		this.kind = kind;
		this.reg_date = reg_date;
		this.image_url = image_url;
		this.ave_grade = ave_grade;
		this.like_cnt = like_cnt;
		this.review_cnt = review_cnt;
	}
	
	public RestaurantDto(int code, String res_name, String address, String res_phone, 
			String kind, String image_url, double ave_grade, int like_cnt, int review_cnt) {
		super();
		this.code = code;
		this.res_name = res_name;
		this.address = address;
		this.res_phone = res_phone;
		this.kind = kind;
		this.image_url = image_url;
		this.ave_grade = ave_grade;
		this.like_cnt = like_cnt;
		this.review_cnt = review_cnt;
	}
	


	public int getCode() {
		return code;
	}

//	public void setCode(int code) {
//		this.code = code;
//	}

	public String getRes_name() {
		return res_name;
	}

	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRes_phone() {
		return res_phone;
	}

	public void setRes_phone(String res_phone) {
		this.res_phone = res_phone;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

//	public void setReg_date(Timestamp reg_date) {
//		this.reg_date = reg_date;
//	}

	public Timestamp getMod_date() {
		return mod_date;
	}

	public void setMod_date(Timestamp mod_date) {
		this.mod_date = mod_date;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public double getAve_grade() {
		return ave_grade;
	}

	public void setAve_grade(double ave_grade) {
		this.ave_grade = ave_grade;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}

	public int getReview_cnt() {
		return review_cnt;
	}

	public void setReview_cnt(int review_cnt) {
		this.review_cnt = review_cnt;
	}
}
