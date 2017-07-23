package notes.framework;

import java.sql.Connection;

public class ConnectionPool {

	private static DataBasePool connPool = null;

	private final static String url = "jdbc:mysql://localhost:3306/note?userUnicode=true&characterEncoding=utf-8&&useSSL=false";

	private final static String user = "apone";

	private final static String password = "3177787";

	private final static int maxConn = 30;

	static {
		connPool = new DataBasePool(url, user, password, maxConn);
	}

	public static Connection getConnection() {
		return connPool.getConnection();
	}

	public static void freeConn(Connection conn) {
		connPool.freeCurrentConnecting(conn);
	}

	public static void closeAllConn() {
		connPool.closeAll();
	}
}
