package notes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import notes.bean.User;
import notes.dao.UserDao;
import notes.framework.ConnectionPool;

public class UserDaoImpl implements UserDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public boolean addUser(User user) {
		String sql = "insert into users(userName,password,gender,head,regTime) values(?,?,?,?,?)";
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		try {
			conn = ConnectionPool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getGender());
			pstmt.setString(4, user.getHead());
			pstmt.setString(5, time);
			pstmt.execute();

			return pstmt.getUpdateCount() != 0;
		} catch (SQLException e) {
			return false;
		} finally {
			ConnectionPool.freeConn(conn);
		}
	}

	@Override
	public User findUser(int userId) {
		String sql = "select * from users where userId=?";
		User user = null;
		try {
			conn = ConnectionPool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user=new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getInt("gender"));
				user.setHead(rs.getString("head"));
				user.setRegTime(rs.getDate("regTime").toString());
			}

			return user;
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionPool.freeConn(conn);
		}
	}

	@Override
	public User findUser(String userName, String password) {
		String sql = "select * from users where userName=? and password=?";
		User user = null;

		try {
			conn = ConnectionPool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if(rs.next()){
				user=new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getInt("gender"));
				user.setHead(rs.getString("head"));
				user.setRegTime(rs.getDate("regTime").toString());
			}

			return user;
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionPool.freeConn(conn);
		}
	}
}
