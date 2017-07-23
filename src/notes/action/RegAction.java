package notes.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import notes.bean.User;
import notes.dao.UserDao;
import notes.model.UserDaoImpl;

import java.util.Map;

/**
 * Created by APone on 2017/5/22.
 * 注册action
 */
public class RegAction extends ActionSupport {

    private String userName;

    private String password;

    private String passAgain;

    private int gender;

    private String time;

    private String head;

    @Override
    public String execute() throws Exception {
        User user = new User();
        user.setGender(gender);
        user.setHead(head);
        user.setPassword(password);
        user.setRegTime(time);
        user.setUserName(userName);

        UserDao userDao = new UserDaoImpl();
        return userDao.addUser(user) ? SUCCESS : INPUT;
    }

    public String getPassAgain() {
        return passAgain;
    }

    public void setPassAgain(String passAgain) {
        this.passAgain = passAgain;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

}
