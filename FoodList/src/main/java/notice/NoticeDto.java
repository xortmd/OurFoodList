package notice;

import java.sql.Timestamp;

public class NoticeDto {
	private int no;
	private String title;
	private String content;
	private String user_id;
	private Timestamp reg_date;
	private int view_cnt;
	private int highlight;
	
	public NoticeDto(int no, String title, String content, String user_id, Timestamp reg_date, int view_cnt, int highlight) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.reg_date = reg_date;
		this.view_cnt = view_cnt;
		this.highlight = highlight;
	}
	
	// WRITE를 위한 생성자
	public NoticeDto(String title, String content, String user_id, int highlight) {
		super();
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.highlight = highlight;
	}
	
	
	// UPDATE를 위한 생성자
	public NoticeDto(int no, String title, String content, int highlight) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.highlight = highlight;
		
	}

	public int getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getUser_id() {
		return user_id;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public int getView_cnt() {
		return view_cnt;
	}
	
	public int getHighlight() {
		return highlight;
	}

//	public void setNo(int no) {
//		this.no = no;
//	}
 
	public void setTitle(String title) {
		this.title = title;
	}
 
	public void setContent(String content) {
		this.content = content;
	}

//	public void setUser_id(String user_id) {
//		this.user_id = user_id;
//	}

//	public void setReg_date(Timestamp reg_date) {
//		this.reg_date = reg_date;
//	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	
	public void setHighlight(int highlight) {
		this.highlight = highlight;
	}
	
	
	

}
