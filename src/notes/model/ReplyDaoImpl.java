package notes.model;

import notes.bean.Reply;
import notes.dao.ReplyDao;
import notes.dao.UserDao;
import notes.framework.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by APone on 2017/6/1.
 * 回复实现
 */
public class ReplyDaoImpl implements ReplyDao {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public boolean addReply(Reply reply) {
        String sql = "insert into reply(noteId,userId,replyContent,replyTime) values(?,?,?,?);";
        try {
            conn = ConnectionPool.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reply.getNoteId());
            pstmt.setInt(2, reply.getUser().getUserId());
            pstmt.setString(3, reply.getReplyContent());
            pstmt.setString(4, reply.getReplyTime());
            pstmt.execute();

            return pstmt.getUpdateCount() != 0;
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionPool.freeConn(conn);
        }
    }

    @Override
    public List<Reply> getRepliesByNoteId(int noteId) {
        List<Reply> list = new ArrayList<>();
        String sql = "select * from reply where noteId=? order by replyTime desc;";
        try {
            conn = ConnectionPool.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noteId);
            rs = pstmt.executeQuery();
            ConnectionPool.freeConn(conn);
            UserDao userDao = new UserDaoImpl();

            while (rs.next()) {
                Reply reply = new Reply();
                reply.setReplyId(rs.getInt("id"));
                reply.setNoteId(rs.getInt("noteId"));
                reply.setUser(userDao.findUser(rs.getInt("userId")));
                reply.setReplyContent(rs.getString("replyContent"));
                reply.setReplyTime(rs.getString("replyTime"));
                list.add(reply);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean delete(int replyId){
        String sql = "delete from reply where id=?";
        try {
            conn = ConnectionPool.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, replyId);
            return pstmt.executeUpdate() != 0;

        } catch (SQLException e) {
            return false;
        }finally {
            ConnectionPool.freeConn(conn);
        }
    }
}
