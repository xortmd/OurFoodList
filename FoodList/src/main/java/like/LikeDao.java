package like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import restaurant.RestaurantDao;
import restaurant.RestaurantDto;
import util.DBManager;

public class LikeDao {
	
	private String url;
	private String user;
	private String password;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	private LikeDao() {
		this.conn = null;
		this.pstmt = null;
		this.rs = null;
		
		this.url = "jdbc:mysql://database-1.cjdszylzp7ot.ap-northeast-2.rds.amazonaws.com:3306/myFavResto";
		this.user = "admin";
		this.password = "8Ba&$#W1m#c4";
		
	}
	
	private static LikeDao instance = new LikeDao();
	public static LikeDao getInstance() {
		return instance;
	}
	
	
	// CRUD
	
	// 1. CREATE
	public void createLike(LikeDto like) {
		String sql = "INSERT INTO `like` VALUES(?, ?, ?, ?)";
		int like_code = likeCodeGenerator();
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, like.getUser_id());
			this.pstmt.setInt(2, like.getResto_code());
			this.pstmt.setInt(3, like_code);
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			this.pstmt.setTimestamp(4, now);
			
			this.pstmt.execute();
			
			System.out.println("CREATE SUCCESS");
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CREATE FAIL");
			
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private int likeCodeGenerator() {
		String sql = "SELECT MAX(like_code) FROM `like`;";
		int code = 0;
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = pstmt.executeQuery();
			
			if(this.rs.next()) {
				code = this.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return ++code;
	}
	
	// 2. READ
	
	// all
	public ArrayList<LikeDto> getLikeAll(){
		ArrayList<LikeDto> likelist = new ArrayList<LikeDto>();
		String sql = "SELECT * FROM `like`";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				String user_id = this.rs.getString(1);
				int resto_code = this.rs.getInt(2);
				int like_code = this.rs.getInt(3);
				Timestamp reg_date = this.rs.getTimestamp(4);
				
				LikeDto like = new LikeDto(user_id, resto_code, like_code, reg_date);
				likelist.add(like);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.conn.close();
				this.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return likelist;
	}
	
	// one
	public LikeDto getLikeById(String user_id) {
		LikeDto like = null;
		String sql = "SELECT * FROM `like` WHERE user_id = ?";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user_id);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				int resto_code = this.rs.getInt(2);
				int like_code = this.rs.getInt(3);
				Timestamp reg_date = this.rs.getTimestamp(4);
				
				like = new LikeDto(user_id, resto_code, like_code, reg_date);
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
		
		return like;
	}
	
	// DELETE
	public void deleteLike(int like_code) {

		String sql = "DELETE FROM `like` WHERE like_code = ?";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, like_code);	
			this.pstmt.execute();

			System.out.println("DELETE SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DELETE FAIL");
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
	
	// 정택승
	public void restaurantLikePlus(int resto_code) {
		String sql = "UPDATE restaurant SET like_cnt = restaurant.like_cnt + 1 WHERE `code` = ?;";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, resto_code);
			this.pstmt.execute();
			System.out.println("restaurant like++ 완료");
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
	
	public void restaurantLikeMinus(int resto_code) {
		String sql = "UPDATE restaurant SET like_cnt = restaurant.like_cnt - 1 WHERE `code` = ?;";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, resto_code);
			this.pstmt.execute();
			System.out.println("restaurant like-- 완료");
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
	
	public int myLikeExist(String user_id, int resto_code) {
		int like_code = -1;
		String sql = "SELECT like_code FROM `like` WHERE user_id = ? AND resto_code = ?;";
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user_id);
			this.pstmt.setInt(2, resto_code);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				like_code = this.rs.getInt(1);
				System.out.println(like_code);
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
		return like_code;
	}
	
	public ArrayList<RestaurantDto> myLikeRestaurant(String user_id) {
		ArrayList<RestaurantDto> list = new ArrayList<>();
		RestaurantDao dao = RestaurantDao.getInstance();
		String sql = "SELECT resto_code FROM `like` WHERE user_id = ?;";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user_id);
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				int resto_code = this.rs.getInt(1);
				RestaurantDto restaurant = dao.getRestaurantByCode(resto_code);
				list.add(restaurant);
			}
			
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
		return list;
	}
	
}
