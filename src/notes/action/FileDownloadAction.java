package notes.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by APone on 2017/5/29.
 * 附件下载Action类
 */
public class FileDownloadAction extends ActionSupport {

    private String fileName;

    private String downloadDir;

    public InputStream getInputStream() throws Exception {
        String path = ServletActionContext.getServletContext().getRealPath(downloadDir);
        String downloadFile = path + "\\" + fileName;
        File file = new File(downloadFile);
        return new FileInputStream(file);
    }

    public String execute() throws Exception{
        return SUCCESS;
    }

    public String getDownloadFileName(){
        String downFileName=fileName;
        try{
            downFileName=new String(downFileName.getBytes(),"ISO-8859-1");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return downFileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDownloadDir(String downloadDir) {
        this.downloadDir = downloadDir;
    }
}
