package notes.action;

import com.opensymphony.xwork2.ActionContext;
import notes.bean.Note;
import notes.bean.User;
import notes.dao.NoteDao;
import notes.model.NoteDaoImpl;
import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * Created by APone on 2017/5/31.
 * note操作action类
 */
public class NoteCtrlAction {

    private Note note;

    private int noteId;

    private String uploadDir;

    private File attachment;

    private String attachmentFileName;

    private String attachmentContentType;

    public String delete(){
        NoteDao noteDao=new NoteDaoImpl();
        noteDao.deleteNote(noteId);
        return SUCCESS;
    }

    public String add() {

        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        User user = (User) session.get("user");
        if (attachment != null) {
            upload();
        }

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        note.setPubTime(time);
        note.setUser(user);
        NoteDao noteDao = new NoteDaoImpl();
        noteDao.addNote(note);
        return SUCCESS;
    }

    private void upload() {
        String path = ServletActionContext.getServletContext().getRealPath(uploadDir);
        File dir = new File(path);
        //存放附件的目录如果不存在，则创建
        if (!dir.exists()) {
            dir.mkdir();
        }

        //将毫秒数作为新文件名
        String newFileName;
        long now = new Date().getTime();
        int index = attachmentFileName.lastIndexOf('.');

        //判断文件是否有扩展名
        if (index != -1) {
            newFileName = now + this.attachmentFileName.substring(index);
        } else {
            newFileName = Long.toString(now);
        }

        byte[] buf = new byte[4096];
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(attachment);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(dir, newFileName));
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            int len;
            while ((len = bufferedInputStream.read(buf)) != -1) {
                bufferedOutputStream.write(buf, 0, len);
            }

            String content = note.getContent();
            note.setContent(content);
            note.setAttachment(newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public String getAttachmentContentType() {
        return attachmentContentType;
    }

    public void setAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }
}
