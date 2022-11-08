package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.DBManager;

public class NoticeDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String url;
	private String user;
	private String password;
	
	private NoticeDao() {
		
		this.conn = null;
		this.pstmt = null;
		this.rs = null;
		
	}
	
	private static NoticeDao instance = new NoticeDao();
	
	public static NoticeDao getInstance() {
		return instance;
		
	}
	
	
	//CRUD
	
	// 1. CREATE 
	public void createNotice(NoticeDto notice) {
		String sql = "INSERT INTO notice VALUES(?, ?, ?, ?, ?, ?, ?)";
		int no = noGenerator();
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			this.pstmt.setString(2, notice.getTitle());
			this.pstmt.setString(3, notice.getContent());
			this.pstmt.setString(4, notice.getUser_id());
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			this.pstmt.setTimestamp(5, now);
			this.pstmt.setInt(6, 0);
			this.pstmt.setInt(7, notice.getHighlight());
			
			this.pstmt.execute();
			
			System.out.println("CREATE SUCCESS!!");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
	}
	
	private int noGenerator() {
		String sql = "SELECT MAX(no) FROM notice;";
		int no = 0;

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			if (this.rs.next()) {
				no = this.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ++no;
	}
	
	
	// 2.READ
	// 2-1. ALL
	public ArrayList<NoticeDto> getNoticeAll(){
		ArrayList<NoticeDto> list = new ArrayList<NoticeDto>();
		String sql = "SELECT * FROM notice ORDER BY `no` DESC";
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				int no = this.rs.getInt(1);
				String title = this.rs.getString(2);
				String contnet = this.rs.getString(3);
				String user_id = this.rs.getString(4);
				Timestamp reg_date = this.rs.getTimestamp(5);
				int view_cnt = this.rs.getInt(6);
				int highlight = this.rs.getInt(7);
				
				NoticeDto notice = new NoticeDto(no, title, contnet, user_id, reg_date, view_cnt, highlight);
				list.add(notice);		
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.conn.close();
				this.pstmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	
	// 2-2. one
	public NoticeDto getNoticeByNo(int no) {
		NoticeDto notice = null;
		String sql = "SELECT * FROM notice WHERE `no` = ?";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				String title = this.rs.getString(2);
				String content = this.rs.getString(3);
				String user_id = this.rs.getString(4);
				Timestamp reg_date = this.rs.getTimestamp(5);
				int view_cnt = this.rs.getInt(6);
				int highlight = this.rs.getInt(7);
			
				notice = new NoticeDto(no, title, content, user_id, reg_date, view_cnt, highlight);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return notice;
	}
	
	// notice의 전체 조회수 구하기 (11.08 김선준 작업)

	public int getTotalView_CntOnNotice() {
		int totalViewNotice = 0;
		String sql = "SELECT SUM(view_cnt) FROM notice;";
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				totalViewNotice = this.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return totalViewNotice;
	}

	
	// 3. Update
	public void updateNotice(NoticeDto notice) {
		
		String sql = "update notice set title = ?, content = ?, highlight = ? where `no` = ?";
		
		int no = notice.getNo();
		String title = notice.getTitle();
		String content = notice.getContent();
		int highlight = notice.getHighlight();
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, title);
			this.pstmt.setString(2, content);
			this.pstmt.setInt(3, highlight);
			this.pstmt.setInt(4, no);
			this.pstmt.execute();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// 조회수 Up
	public void addViewCnt(int no) {
		int beforeViewCnt = getNoticeByNo(no).getView_cnt();
		
		String sql = "UPDATE notice SET view_cnt=? WHERE `no`=?";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, beforeViewCnt+1);
			this.pstmt.setInt(2, no);
			this.pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 4. DELETE
	
	public void removeNotice(int no) {
		String sql = "DELETE FROM notice WHERE no=?";

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			this.pstmt.execute();

			System.out.println("DELETE SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DELETE FAIL");
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
}
