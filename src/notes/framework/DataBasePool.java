package notes.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * ���ӳ�
 */
public class DataBasePool {

	private static String driver = "com.mysql.jdbc.Driver";

	private int maxConn;
	private int connectingCount = 0;

	private String url;
	private String user;
	private String password;

	private ArrayList<Connection> freeConn;

	public DataBasePool(String url, String user, String password, int maxConn) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.maxConn = maxConn;
		this.freeConn = new ArrayList<Connection>();
	}

	public synchronized Connection getConnection() {

		Connection conn = null;
		if (freeConn.size() > 0) {
			conn = freeConn.get(0);
			freeConn.remove(0);
			freeConn.trimToSize();
			try {
				if (conn.isClosed()) {
					conn = getConnection();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (connectingCount < maxConn) {
			conn = getNewConnection();
		}

		if (conn != null) {
			connectingCount++;
		}

		return conn;
	}

	public synchronized Connection getConnection(long timeout) {

		Connection conn = null;

		while ((conn = getConnection()) == null) {
			try {
				wait(timeout);
			} catch (InterruptedException e) {
				return null;
			}
		}
		return conn;
	}

	private synchronized Connection getNewConnection() {
		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public synchronized void freeCurrentConnecting(Connection conn) {
		freeConn.add(conn);
		connectingCount--;

		notifyAll();
	}

	public synchronized void closeAll() {
		Iterator<Connection> iter = freeConn.iterator();
		Connection conn = null;

		while (iter.hasNext()) {
			try {
				conn = iter.next();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		freeConn.clear();
	}

	public synchronized int getMaxConn() {// ��ȡ�����������
		return maxConn;
	}

	public int getConnectingCount() {// ��ȡ��ǰ������
		return connectingCount;
	}
}