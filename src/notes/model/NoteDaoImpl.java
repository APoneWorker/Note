package notes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import notes.bean.Note;
import notes.bean.User;
import notes.dao.NoteDao;
import notes.framework.ConnectionPool;

public class NoteDaoImpl implements NoteDao {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public boolean addNote(Note note) {
        String sql = "insert into notes(content,pubTime,title,userId,attachment) values(?,?,?,?,?);";
        try {
            conn = ConnectionPool.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, note.getContent());
            pstmt.setString(2, note.getPubTime());
            pstmt.setString(3, note.getTitle());
            pstmt.setInt(4, note.getUser().getUserId());
            pstmt.setString(5, note.getAttachment());

            pstmt.execute();

            return pstmt.getUpdateCount() != 0;
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionPool.freeConn(conn);
        }
    }

    @Override
    public List<Note> getAllNotes() {
        List<Note> list = new ArrayList<>();
        String sql = "select * from notes order by pubTime desc;";
        try {
            conn = ConnectionPool.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ConnectionPool.freeConn(conn);

            while (rs.next()) {
                Note note = new Note();
                note.setNoteId(rs.getInt("noteId"));
                note.setTitle(rs.getString("title"));
                User user = new UserDaoImpl().findUser(rs.getInt("userId"));
                note.setUser(user);
                list.add(note);
            }

        } catch (SQLException e) {
            ConnectionPool.freeConn(conn);
            return list;
        }
        return list;
    }

    @Override
    public Note getNoteById(int noteId) {
        String sql = "select * from notes where noteId=?;";
        Note note = new Note();
        try {
            conn = ConnectionPool.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noteId);
            rs = pstmt.executeQuery();
            ConnectionPool.freeConn(conn);

            if (rs.next()) {
                note.setNoteId(rs.getInt("noteId"));
                note.setContent(rs.getString("content"));
                note.setPubTime(rs.getString("pubTime"));
                note.setTitle(rs.getString("title"));
                note.setAttachment(rs.getString("attachment"));

                User user = new UserDaoImpl().findUser(rs.getInt("userId"));
                note.setUser(user);
            }

        } catch (SQLException e) {
            ConnectionPool.freeConn(conn);
        }
        return note;
    }

    @Override
    public boolean deleteNote(int noteId) {
        String sql = "delete from notes where noteId=?";
        try {
            conn = ConnectionPool.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noteId);
            return pstmt.executeUpdate() != 0;

        } catch (SQLException e) {
            return false;
        }finally {
            ConnectionPool.freeConn(conn);
        }
    }
}
