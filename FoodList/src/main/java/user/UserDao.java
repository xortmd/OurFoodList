package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.DBManager;

public class UserDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private UserDao() {
		this.conn = null;
		this.pstmt = null;
		this.rs = null;
	}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	// CRUD
	// create
	public void createUser(UserDto user) {
		String sql = "insert `user` value(?, ?, ?, ?, ?, ?);";

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user.getId());
			this.pstmt.setString(2, user.getPassword());
			this.pstmt.setString(3, user.getName());
			this.pstmt.setString(4, user.getPhone());
			this.pstmt.setTimestamp(5, user.getBirth());
			this.pstmt.setTimestamp(6, user.getReg_date());
			this.pstmt.execute();

			System.out.println("CREATE SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CREATE FAIL");
		} finally {
			try {
//				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Read
	// 1. 전체유저 조회
	public ArrayList<UserDto> getUserAll() {
		ArrayList<UserDto> list = new ArrayList<>();
		String sql = "SELECT * FROM `user`;"; // <- 확인

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			while (this.rs.next()) {
				String id = this.rs.getString(1);
				String password = this.rs.getString(2);
				String name = this.rs.getString(3);
				String phone = this.rs.getString(4);
				Timestamp birth = this.rs.getTimestamp(5);
				Timestamp reg_date = this.rs.getTimestamp(6);

				System.out.println("id : " + id); // 확인용
				System.out.println("name : " + name); // 추후 삭제

				UserDto user = new UserDto(id, password, name, phone, birth, reg_date);

				list.add(user);
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

	// 2-1. 개인 유저조회 (아이디, 패스워드)
	public UserDto getUserByIdPw(String id, String password) {
		UserDto user = null;
		String sql = "SELECT * FROM `user` WHERE id = ? and `password`= ?"; // <- , ?? && ??>

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, id);
			this.pstmt.setString(2, password);
			this.rs = this.pstmt.executeQuery();

			if (this.rs.next()) {
				String name = this.rs.getString(3);
				String phone = this.rs.getString(4);
				Timestamp birth = this.rs.getTimestamp(5);
				Timestamp reg_date = this.rs.getTimestamp(6);

				user = new UserDto(id, password, name, phone, birth, reg_date);
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

		return user;
	}

	// 2-2. 아이디 조회 (아이디)
	public UserDto getUserById(String id) {
		UserDto user = null;
//		boolean dupl = false;
		String sql = "SELECT * FROM `user` WHERE id = ?";
//		use myFavResto;

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, id);
			this.rs = this.pstmt.executeQuery();

			if (this.rs.next()) {
				String password = this.rs.getString(2);
				String name = this.rs.getString(3);
				String phone = this.rs.getString(4);
				Timestamp birth = this.rs.getTimestamp(5);
				Timestamp reg_date = this.rs.getTimestamp(6);
//				
				user = new UserDto(id, password, name, phone, birth, reg_date);
//				dupl = true;
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

		return user;
	}

	// 2-3 전화번호 조회
	public boolean getUserByPhone(String phone) {
//		UserDto user = null;
		boolean dupl = false;
		String sql = "SELECT * FROM `user` WHERE phone = ?";
//		use myFavResto;

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, phone);
			this.rs = this.pstmt.executeQuery();

			if (this.rs.next()) {
//				String password= this.rs.getString(2);
				String name = this.rs.getString(3);
//				String phone = this.rs.getString(4);
				Timestamp birth = this.rs.getTimestamp(5);
				Timestamp reg_date = this.rs.getTimestamp(6);
				System.out.println("핸드폰 번호 중복되는 유저 이름 : " + name);
				System.out.println("핸드폰 번호(dao) : " + phone);

//				user = new UserDto(id, password, name, phone, birth, reg_date);
				dupl = true;
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

		return dupl;
	}

	// 2-4 전화번호 조회로 유저 조회
	public UserDto getUserDtoByPhone(String phone) {
			UserDto user = null;
			String sql = "SELECT * FROM `user` WHERE phone = ?";

			try {
				this.conn = DBManager.getConnection();
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, phone);
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					String id = this.rs.getString(1);
					String password= this.rs.getString(2);
					String name = this.rs.getString(3);
//					String phone = this.rs.getString(4);
					Timestamp birth = this.rs.getTimestamp(5);
					Timestamp reg_date = this.rs.getTimestamp(6);
					System.out.println("핸드폰 번호 중복되는 유저 이름 : " + name);
					System.out.println("핸드폰 번호(dao) : "+ phone);
					
					user = new UserDto(id, password, name, phone, birth, reg_date);
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
			
			return user;
		}
	
	// 유저 수 구하기 (11.08 김선준 작업)
	public int getCountUser() {
		int countUser = 0;
		String sql = "SELECT COUNT(id) FROM `user`;";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				countUser = this.rs.getInt(1) - getCountDltUser();				
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
		return countUser;
	}
	
	public int getCountDltUser() {
		int dltUser = 0;
		String sql = "SELECT COUNT(id) FROM `user` WHERE password= '****' ";
		
		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				dltUser = this.rs.getInt(1);
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
		return dltUser;
	}

	// Update
	public void updateUser(UserDto user) {
		String sql = "update `user` set `password` = ?, `name`=?, phone = ?, birth=?, reg_date=? WHERE id=?;";

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user.getPassword());
			this.pstmt.setString(2, user.getName());
			this.pstmt.setString(3, user.getPhone());
			this.pstmt.setTimestamp(4, user.getBirth());
			this.pstmt.setTimestamp(5, user.getReg_date());
			this.pstmt.setString(6, user.getId());
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

	// Delete
	public void deleteUser(String id, String password) {
		String sql = "DELETE FROM `user` WHERE id = ? and `password` = ?;";

		try {
			this.conn = DBManager.getConnection();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, id);
			this.pstmt.setString(2, password);
			this.pstmt.execute();

			System.out.println("DELETE SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DELETE FAIL");
		} finally {
			try {
//				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
