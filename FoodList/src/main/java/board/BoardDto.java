package board;

import java.sql.Timestamp;

public class BoardDto {
	
	// no title content user  regDate modDate viewCnt 
	private int no;
	private String title;
	private String content;
	private String user_id;
	private Timestamp reg_date;
	private Timestamp mod_date;
	private int view_cnt;
	
	
	public BoardDto(int no, String title, String content, String user_id, Timestamp reg_date, Timestamp mod_date, int view_cnt) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
		this.view_cnt = view_cnt;
	}
	
	public BoardDto(String title, String content, String user_id, Timestamp reg_date) {
		super();
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.reg_date = reg_date;
	}
	
	public BoardDto(String title, String content, String user_id) {
		super();
		this.title = title;
		this.content = content;
		this.user_id = user_id;
	}
	
	public BoardDto(String title, String content, String user_id, Timestamp reg_date, Timestamp mod_date) {
		super();
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
	}
	
	public BoardDto(String title, String content, int no) {
		super();
		this.title = title;
		this.content = content;
		this.no = no;

		
	}

	//get set
	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getUser_id() {
		return user_id;
	}


	public Timestamp getReg_date() {
		return reg_date;
	}


	public Timestamp getMod_date() {
		return mod_date;
	}


	public void setMod_date(Timestamp mod_date) {
		this.mod_date = mod_date;
	}


	public int getView_cnt() {
		return view_cnt;
	}


	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

}