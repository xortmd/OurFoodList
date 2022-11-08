package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.DBManager;
import review.ReviewDao;

// 정택승
public class RestaurantDao {
	
	private String url;
	private String user;
	private String password;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private RestaurantDao() {
		this.url = "jdbc:mysql://database-1.cjdszylzp7ot.ap-northeast-2.rds.amazonaws.com:3306/myFavResto";
		this.user = "admin";
		this.password = "8Ba&$#W1m#c4";
		
		this.conn = null;
		this.pstmt = null;
		this.rs = null;
	}
	
	private static RestaurantDao instance = new RestaurantDao();
	public static RestaurantDao getInstance() {
		return instance;
	}
	
	// CRUD
	// Create
	public void createRestaurant(RestaurantDto restaurant) {
		String sql = "insert into restaurant values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		int code = codeGenerator();
		
		try {			
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, code);
			this.pstmt.setString(2, restaurant.getRes_name());
			this.pstmt.setString(3, restaurant.getAddress());
			this.pstmt.setString(4, restaurant.getRes_phone());
			this.pstmt.setString(5, restaurant.getKind());
			Timestamp now = new Timestamp(System.currentTimeMillis());
			this.pstmt.setTimestamp(6, now);
			this.pstmt.setTimestamp(7, now);
			this.pstmt.setString(8, restaurant.getImage_url());
			this.pstmt.setDouble(9, restaurant.getAve_grade());
			this.pstmt.setInt(10, restaurant.getLike_cnt());
			this.pstmt.setInt(11, restaurant.getReview_cnt());
			
			this.pstmt.execute();
			
			System.out.println("CREATE SUCCESS");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CREATE FAIL");
		} finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int codeGenerator() {
		String sql = "SELECT MAX(`code`) FROM restaurant;";
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
	
	
	// Read
	// 1. All
	public ArrayList<RestaurantDto> getRestaurantAll() {
		ArrayList<RestaurantDto> list = new ArrayList<RestaurantDto>();
		String sql = "SELECT * FROM restaurant ORDER BY `code`;";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				int code = this.rs.getInt(1);
				String res_name = this.rs.getString(2);
				String address = this.rs.getString(3);
				String res_phone = this.rs.getString(4);
//				Timestamp openTime = this.rs.getTimestamp(5);
//				Timestamp closeTime = this.rs.getTimestamp(6);
				String kind = this.rs.getString(5);
				Timestamp reg_date = this.rs.getTimestamp(6);
				String image_url = this.rs.getString(8);
				double ave_grade = this.rs.getDouble(9);
				int like_cnt = this.rs.getInt(10);
				int review_cnt = this.rs.getInt(11);
				
				RestaurantDto restaurant = new RestaurantDto(code, res_name, address, res_phone,
						kind, reg_date, image_url, ave_grade, like_cnt, review_cnt);
				
				list.add(restaurant);
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
		return list;
	}
	
	// 2. One
	public RestaurantDto getRestaurantByCode(int code) {
		RestaurantDto restaurant = null;
		String sql = "SELECT * FROM restaurant WHERE `code` = ?;";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, code);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				String res_name = this.rs.getString(2);
				System.out.println(res_name);
				String address = this.rs.getString(3);
				String res_phone = this.rs.getString(4);
//				Timestamp openTime = this.rs.getTimestamp(5);
//				Timestamp closeTime = this.rs.getTimestamp(6);
				String kind = this.rs.getString(5);
				Timestamp reg_date = this.rs.getTimestamp(6);
//				
				String image_url = this.rs.getString(8);
				double ave_grade = this.rs.getDouble(9);
				int like_cnt = this.rs.getInt(10);
				int review_cnt = this.rs.getInt(11);

				restaurant = new RestaurantDto(code, res_name, address, res_phone,
						kind, reg_date, image_url, ave_grade, like_cnt, review_cnt);

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
		return restaurant;
	}
	
	
	
	// Update
	public void updateRestaurant (RestaurantDto restaurant) {
		String sql = "UPDATE restaurant SET res_name = ?, address = ?, res_phone = ?, "
				+ "kind = ?, mod_date = ?, image_url = ?, ave_grade = ?, loc_x = ?, loc_y = ? WHERE code = ?;";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = conn.prepareStatement(sql);
			
			this.pstmt.setString(1, restaurant.getRes_name());
			this.pstmt.setString(2, restaurant.getAddress());
			this.pstmt.setString(3, restaurant.getRes_phone());
//			this.pstmt.setTimestamp(4, restaurant.getOpenTime());
//			this.pstmt.setTimestamp(5, restaurant.getCloseTime());
			this.pstmt.setString(4, restaurant.getKind());
			Timestamp now = new Timestamp(System.currentTimeMillis());
			this.pstmt.setTimestamp(5, now);
			this.pstmt.setString(6, restaurant.getImage_url());
			this.pstmt.setDouble(7, restaurant.getAve_grade());
			this.pstmt.setInt(8, restaurant.getLike_cnt());
			this.pstmt.setInt(9, restaurant.getReview_cnt());
			this.pstmt.setInt(10, restaurant.getCode());
			
			this.pstmt.execute();
			
			System.out.println("UPDATE SUCCESS");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("UPDATE FAIL");
		} finally {
			try {
				this.conn.close();
				this.pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//평점의 평균을 가져와서 resturant 테이블에 저장
	public void updateRestaurantForAVGgrade(int code) {
		String sql = "UPDATE restaurant SET ave_grade = ? WHERE `code` = ?";
		ReviewDao dao = ReviewDao.getInstance();
		
		double avg_grade = dao.getReviewForAVGgrade(code);
		if(avg_grade<= 0.7) {
			avg_grade = 0.5;
		} else if(avg_grade <= 1.3) {
			avg_grade = 1.0;
		} else if(avg_grade <= 1.7) {
			avg_grade = 1.5;
		} else if(avg_grade <= 2.3) {
			avg_grade = 2.0;
		} else if(avg_grade <= 2.7) {
			avg_grade = 2.5;
		} else if(avg_grade <= 3.3) {
			avg_grade = 3.0;
		} else if(avg_grade <= 3.7) {
			avg_grade = 3.5;
		} else if(avg_grade <= 4.3) {
			avg_grade = 4.0;
		} else if(avg_grade <= 4.7) {
			avg_grade = 4.5;
		} else if(avg_grade <= 5.0) {
			avg_grade = 5.0;
		}
		
		System.out.println("***********"+avg_grade);
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setDouble(1, avg_grade);
			this.pstmt.setInt(2, code);
			this.pstmt.execute();
			System.out.println("레스토랑 테이블에 평점 등록 성공");
			
			
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
		
	}
	
	//리뷰의 갯수 review테이블에서 받아와 restaurant 테이블에 저장하기
	public void updateRestaurantForReviewCnt(int code) {
		String sql = "UPDATE restaurant SET review_cnt = ? WHERE code = ?";
		ReviewDao dao = ReviewDao.getInstance();
		
		int review_cnt = dao.getReviewForReviewCnt(code);
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			
			this.pstmt.setInt(1, review_cnt);
			this.pstmt.setInt(2, code);
			this.pstmt.execute();
			
	
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
//	String sql2 = "UPDATE restaurant SET review_cnt = ? WHERE `code` = ?; ";
	//this.pstmt = this.conn.prepareStatement(sql2);
	//this.pstmt.set

	
	// Delete
	public void deleteRestaurant(RestaurantDto restaurant) {
		String sql = "DELETE FROM restaurant WHERE code = ?;";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = conn.prepareStatement(sql);
			
			this.pstmt.setInt(1, restaurant.getCode());
			
			this.pstmt.execute();
			
			System.out.println("DELETE SUCCESS");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DELETE FAIL");
		} finally {
			try {
				this.conn.close();
				this.pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// SEARCH
	public ArrayList<RestaurantDto> searchRestaurant(String searchKind, String area) {
		ArrayList<RestaurantDto> list = new ArrayList<RestaurantDto>();
		String sql = "";
		try {
			this.conn = DBManager.getConnection();
			if(searchKind.equals("전체") && area.equals("전체")) {
				sql = "SELECT * FROM restaurant;";
				this.pstmt = this.conn.prepareStatement(sql);
			} else if(!searchKind.equals("전체") && area.equals("전체")) {
				sql = "SELECT * FROM restaurant WHERE kind = ?;";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, searchKind);
			} else if(searchKind.equals("전체") && !area.equals("전체")) {
				sql = "SELECT * FROM restaurant WHERE address LIKE ?;";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, "%" + area + "%");
			} else {
				sql = "SELECT * FROM restaurant WHERE kind = ? AND address LIKE ?;";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, searchKind);
				this.pstmt.setString(2, "%" + area + "%");
			}
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				int code = this.rs.getInt(1);
				String res_name = this.rs.getString(2);
				String address = this.rs.getString(3);
				String res_phone = this.rs.getString(4);
				String kind = this.rs.getString(5);
				Timestamp reg_date = this.rs.getTimestamp(6);
//				Timestamp mod_date = this.rs.getTimestamp(7);
				String image_url = this.rs.getString(8);
				double ave_grade = this.rs.getDouble(9);
				int like_cnt = this.rs.getInt(10);
				int review_cnt = this.rs.getInt(11);
				
				RestaurantDto restaurant = new RestaurantDto(code, res_name, address, res_phone,
						kind, reg_date, image_url, ave_grade, like_cnt, review_cnt);
				
				list.add(restaurant);
			}
			
			System.out.println("SEARCH SUCCESS");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SEARCH FAIL");
		} finally {
			try {
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// SORT
	public ArrayList<RestaurantDto> sortRestaurant(ArrayList<RestaurantDto> list, String sort) {
		if(sort.equals("평점순")) {
			for(int i = 0; i < list.size() - 1; i++) {
				for(int j = i + 1; j < list.size(); j++) {
					if(list.get(i).getAve_grade() < list.get(j).getAve_grade()) {
						RestaurantDto temp = list.get(i);
						list.set(i, list.get(j));
						list.set(j, temp);
					}
				}
			}
		} else if(sort.equals("좋아요순")) {
			for(int i = 0; i < list.size() - 1; i++) {
				for(int j = i + 1; j < list.size(); j++) {
					if(list.get(i).getLike_cnt() < list.get(j).getLike_cnt()) {
						RestaurantDto temp = list.get(i);
						list.set(i, list.get(j));
						list.set(j, temp);
					}
				}
			}
		} else if(sort.equals("리뷰순")) {
			for(int i = 0; i < list.size() - 1; i++) {
				for(int j = i + 1; j < list.size(); j++) {
					if(list.get(i).getReview_cnt() < list.get(j).getReview_cnt()) {
						RestaurantDto temp = list.get(i);
						list.set(i, list.get(j));
						list.set(j, temp);
					}
				}
			}
		}
		return list;
	}
}