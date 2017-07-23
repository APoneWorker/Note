package notes.dao;

import notes.bean.Reply;

import java.util.List;

/**
 * Created by APone on 2017/6/1.
 * 回复接口
 */
public interface ReplyDao {

    boolean addReply(Reply reply);

    List<Reply> getRepliesByNoteId(int noteId);

    boolean delete(int replyId);
}
