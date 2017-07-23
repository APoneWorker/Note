package notes.action;

import notes.bean.Note;
import notes.bean.Reply;
import notes.dao.NoteDao;
import notes.dao.ReplyDao;
import notes.model.NoteDaoImpl;
import notes.model.ReplyDaoImpl;

import java.util.List;

import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * Created by APone on 2017/5/21.
 * 留言显示action
 */
public class NoteShowAction {

    private Note note;

    private List<Note> notes;

    private List<Reply> replies;

    private int noteId;

    public String list() {
        NoteDao noteDao = new NoteDaoImpl();
        notes = noteDao.getAllNotes();
        return SUCCESS;
    }

    public String detail() {
        NoteDao noteDao = new NoteDaoImpl();
        ReplyDao replyDao=new ReplyDaoImpl();
        note = noteDao.getNoteById(noteId);
        replies=replyDao.getRepliesByNoteId(noteId);
        return SUCCESS;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}
