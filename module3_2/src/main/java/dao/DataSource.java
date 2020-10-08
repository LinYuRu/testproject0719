package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class DataSource {
	private static final String connUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String Act = "system";
	private static final String Pwd = "sa123";
	private static final int n = 3;// 連線數
	private LinkedList<Connection> ll;
	private static DataSource dataSource;

	public static DataSource getInstance() {
		if (dataSource == null) {
			synchronized (DataSource.class) {
				if (dataSource == null) {
					dataSource = new DataSource();
				}
			}
		}
		return dataSource;
	}

	private DataSource() {
		System.out.println("Connection");
		ll = new LinkedList<Connection>();
		// 當建立MyDataSource時就會向集合中新增10個Connection物件。

		for (int i = 0; i < n; i++) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				ll.add(DriverManager.getConnection(connUrl, Act, Pwd));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 獲取連線,將集合中的connection返回一個
	public Connection getConnection() {
		return ll.removeLast();
	}

	// 關閉連線，將連線物件重新再放回集合中去
	public void closeConnection(Connection con) {
		ll.addFirst(con);
		System.out.println("Recycle connection");
	}
}
