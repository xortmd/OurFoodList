 package review;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


import util.DBManager;
import restaurant.RestaurantDao;
import restaurant.RestaurantDto;

public class ReviewDao {

	private String url;
	private String user;
	private String password;

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ReviewDao() {
		this.conn = null;
		this.pstmt = null;
		this.rs = null;

//		this.url = "jdbc:mysql://database-1.cjdszylzp7ot.ap-northeast-2.rds.amazonaws.com:3306/myFavResto";
//		this.user = "admin";
//		this.password = "8Ba&$#W1m#c4";
	}
	
	private static ReviewDao instance = new ReviewDao();
	
	public static ReviewDao getInstance() {
		return instance;
		
	}
	
	//CRUD
	// 1. CREATE
	public void createReview(ReviewDto review) {
		String sql = "insert into review values(?, ?, ?, ?, ?, ?)";
		int no = noGenerator();
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			this.pstmt.setInt(2, review.getResto_code());
			this.pstmt.setString(3, review.getComent());
			this.pstmt.setString(4, review.getUser_id());
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			this.pstmt.setTimestamp(5, now);
			this.pstmt.setDouble(6, review.getGive_grade());
			
			this.pstmt.execute();
			System.out.println("리뷰 생성 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 생성 실패");
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private int noGenerator() {
		String sql = "SELECT MAX(no) FROM review;";
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
	// ALL
	public ArrayList<ReviewDto> getReviewAll(){
		ArrayList<ReviewDto> reviewlist = new ArrayList<ReviewDto>();
		String sql = "SELECT * FROM review";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				int no = this.rs.getInt(1);
				int resto_code = this.rs.getInt(2);
				String coment = this.rs.getString(3);
				String user_id = this.rs.getString(4);
				Timestamp regdate = this.rs.getTimestamp(5);
				double give_grade = this.rs.getDouble(6);
				
				ReviewDto review = new ReviewDto(no, resto_code, coment, user_id, regdate, give_grade);
				reviewlist.add(review);
				System.out.println("리뷰 로드 성공");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 로드 실패");
		} finally {
			try {
				this.conn.close();
				this.rs.close();
				this.pstmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return reviewlist;
	}
	
	//RestoCode의 리뷰 불러오기
	public ArrayList<ReviewDto> getReviewAllByRestoCode(int resto_code){
		ArrayList<ReviewDto> reviewlist = new ArrayList<ReviewDto>();
		String sql = "SELECT * FROM review WHERE resto_code = ?";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, resto_code);
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				int no = this.rs.getInt(2);
				String coment = this.rs.getString(3);
				String user_id = this.rs.getString(4);
				Timestamp regdate = this.rs.getTimestamp(5);
				double give_grade = this.rs.getDouble(6);
				
				ReviewDto review = new ReviewDto(no, resto_code, coment, user_id, regdate, give_grade);
				reviewlist.add(review);
				System.out.println("리뷰 로드 성공");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 로드 실패");
		} finally {
			try {
				this.conn.close();
				this.rs.close();
				this.pstmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return reviewlist;
	}
	
	// user_id의 리뷰 리스트 불러오기
	public ArrayList<ReviewDto> getReviewAllByUser_id(String user_id){
		ArrayList<ReviewDto> list = new ArrayList<>();
		String sql = "SELECT * FROM review WHERE user_id = ?";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user_id);
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				int no = this.rs.getInt(1);
				int resto_code = this.rs.getInt(2);
				String comment = this.rs.getString(3);
				Timestamp reg_date = this.rs.getTimestamp(5);
				double give_grade = this.rs.getDouble(6);
				
				RestaurantDao dao = RestaurantDao.getInstance();
				RestaurantDto resto = dao.getRestaurantByCode(resto_code);
				String resto_name = resto.getRes_name();
				
				ReviewDto review = new ReviewDto(no, resto_code, comment, user_id, reg_date, give_grade);
				
				list.add(review);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.conn.close();
				this.rs.close();
				this.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		
		return list;
	}
	
	// ONE
	public ReviewDto getReviewByReviewCode(int no) {
		ReviewDto review = null;
		String sql = "SELECT * FROM review WHERE `no` = ?";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				int resto_code = this.rs.getInt(2);
				String coment = this.rs.getString(3);
				String user_id = this.rs.getString(4);
				Timestamp regdate = this.rs.getTimestamp(5);
				double give_grade = this.rs.getDouble(6);
				
				review = new ReviewDto(no, resto_code, coment, user_id, regdate, give_grade);
				System.out.println("리뷰 로드 성공");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 로드 실패");

		} finally {
			try {
				this.rs.close();
				this.conn.close();
				this.pstmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return review;
	}
	
	//give_grade의 평균값 구하기
		public double getReviewForAVGgrade(int resto_code) {
			String sql = "SELECT AVG(give_grade) FROM review WHERE resto_code = ?";
			double avg_grade = 0.0;
			try {
				this.conn = DBManager.getConnection();
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, resto_code);
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					avg_grade = this.rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} try {
				this.rs.close();
				this.conn.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return avg_grade;
		}
		
		
		
		// 리뷰의 개수 구하기
		public int getReviewForReviewCnt(int resto_code) {
			String sql = "SELECT COUNT(*) FROM review WHERE resto_code = ?;";
			int review_cnt = 0;
			
			try {
				this.conn = DBManager.getConnection();
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, resto_code);
				this.rs = this.pstmt.executeQuery();

				
				if(this.rs.next()) {
					review_cnt = this.rs.getInt(1);
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					this.conn.close();
					this.pstmt.close();
					this.rs.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			return review_cnt;
		}
		
	

	
//	//UPDATE
//	public void updateReviewInfo(ReviewDto review) {
//		String sql = "update review set rev_content = ?, mod_date = ?";
//		
//		
//		String rev_content = review.getRev_content();
//		
//		
//		try {
//			this.conn = DBManager.getConnection(this.url, this.user, this.password);
//			this.pstmt = this.conn.prepareStatement(sql);
//			this.pstmt.setString(1, rev_content);
//			this.pstmt.setTimestamp(2, mod_date);
//			
//			this.pstmt.execute();
//			
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				this.rs.close();
//				this.conn.close();
//				this.pstmt.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
//	
	//DELETE
	public void deleteReview(ReviewDto review) {
		
		String sql = "DELETE FROM reivew WHERE `no` = ?";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, review.getNo());
			this.pstmt.execute();
			
			System.out.println("리뷰 삭제 성공");

			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 삭제 실패");

		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	public ArrayList<RestaurantDto> myReviewRestaurant(String user_id) {
		ArrayList<RestaurantDto> list = new ArrayList<>();
		RestaurantDao dao = RestaurantDao.getInstance();
		String sql = "SELECT resto_code FROM review WHERE user_id = ?;";
		
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
