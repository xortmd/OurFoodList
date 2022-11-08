package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;
			
		String url = "jdbc:mysql://database-1.cjdszylzp7ot.ap-northeast-2.rds.amazonaws.com:3306/myFavResto";
		String user = "admin";
		String password = "8Ba&$#W1m#c4";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("데이터 베이스 연동 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연동 실패");
		}
		return conn;
	}
	
}
