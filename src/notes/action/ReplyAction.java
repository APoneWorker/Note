package notes.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import notes.bean.Reply;
import notes.bean.User;
import notes.dao.ReplyDao;
import notes.model.ReplyDaoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by APone on 2017/6/1.
 * 回复Action类
 */
public class ReplyAction extends ActionSupport{

    private int noteId;

    private Reply reply;

    private int replyId;

    public String addReply(){

        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        User user = (User) session.get("user");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        reply.setUser(user);
        reply.setReplyTime(time);
        reply.setNoteId(noteId);

        ReplyDao replyDao=new ReplyDaoImpl();
        replyDao.addReply(reply);
        return SUCCESS;
    }

    public String delete(){
        ReplyDao replyDao=new ReplyDaoImpl();
        replyDao.delete(replyId);
        return SUCCESS;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
}
