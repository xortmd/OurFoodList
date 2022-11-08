package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.DBManager;

public class BoardDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private String url;
	private String user;
	private String password;

	// Singletone으로 구현
	private BoardDao() {
//		this.url = "jdbc:mysql://database-1.cjdszylzp7ot.ap-northeast-2.rds.amazonaws.com:3306/myFavResto";
//		this.user = "admin";
//		this.password = "8Ba&$#W1m#c4";

		this.conn = null;
		this.pstmt = null;
		this.rs = null;
	}

	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	// CRUD
	// Create
	public void createBoard(BoardDto board) {
		String sql = "INSERT INTO board VALUES(?,?,?,?,?,?,?);";
		int no = noGenerator();

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			this.pstmt.setString(2, board.getTitle());
			this.pstmt.setString(3, board.getContent());
			this.pstmt.setString(4, board.getUser_id());

			Timestamp now = new Timestamp(System.currentTimeMillis());
			this.pstmt.setTimestamp(5, now);
			this.pstmt.setTimestamp(6, now);
			this.pstmt.setInt(7, 0);

			this.pstmt.execute();

			System.out.println("CREATE SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CREATE FAIL");
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private int noGenerator() {
		String sql = "SELECT MAX(no) FROM board;";
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

	// Read
	// 1. All
	public ArrayList<BoardDto> getBoardAll() {
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM board ORDER BY `no` DESC";

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			while (this.rs.next()) {
				int no = this.rs.getInt(1);
				String title = this.rs.getString(2);
				String content = this.rs.getString(3);
				String user_id = this.rs.getString(4);
				Timestamp reg_date = this.rs.getTimestamp(5);
				Timestamp mod_date = this.rs.getTimestamp(6);
				int view_cnt = this.rs.getInt(7);

				BoardDto board = new BoardDto(no, title, content, user_id, reg_date, mod_date, view_cnt);
				list.add(board);
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
		return list;
	}

	// 2. one
	public BoardDto getBoardByNo(int no) {
		BoardDto board = null;
		String sql = "SELECT * FROM board WHERE `no` = ?";

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			this.rs = this.pstmt.executeQuery();

			if (this.rs.next()) {
				String title = this.rs.getString(2);
				String content = this.rs.getString(3);
				String user_id = this.rs.getString(4);
				Timestamp reg_date = this.rs.getTimestamp(5);
				Timestamp mod_date = this.rs.getTimestamp(6);
				int view_cnt = this.rs.getInt(7);

				board = new BoardDto(no, title, content, user_id, reg_date, mod_date, view_cnt);
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
		return board;
	}
	
	// 3. one by user
		public ArrayList<BoardDto> getBoardById(String id) {
			ArrayList<BoardDto> list = new ArrayList<>();
			String sql = "SELECT * FROM board WHERE user_id = ?";

			try {
				this.conn = DBManager.getConnection();
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int no = this.rs.getInt(1);
					String title = this.rs.getString(2);
					String content = this.rs.getString(3);
//					String user_id = this.rs.getString(4);
					Timestamp reg_date = this.rs.getTimestamp(5);
					Timestamp mod_date = this.rs.getTimestamp(6);
					int view_cnt = this.rs.getInt(7);

					BoardDto board = new BoardDto(no, title, content, id, reg_date, mod_date, view_cnt);
				
					list.add(board);
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
			return list;
		}
	

	// Update
	public void updateBoard(BoardDto board) {

		String sql = "update board set title = ?, content = ? where `no` = ?;";

		int no = board.getNo();
		String title = board.getTitle();
		String content = board.getContent();

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, title);
			this.pstmt.setString(2, content);
			this.pstmt.setInt(3, no);
			this.pstmt.execute();
			
			System.out.println("UPDATE SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UPDATE FAIL");
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 조회수 Up
	public void addViewCnt(int no) {
		int beforeViewCnt = getBoardByNo(no).getView_cnt();
		
		String sql = "UPDATE board SET view_cnt=? WHERE `no`=?";
		
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

	// Delete
	public void removeMember(int no) {
		String sql = "DELETE FROM board WHERE no=?";

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