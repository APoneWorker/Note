package notes.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import notes.bean.User;
import notes.dao.UserDao;
import notes.model.UserDaoImpl;

import java.util.Map;

/**
 * Created by APone on 2017/5/21.
 * 登录action
 */
public class LoginAction extends ActionSupport {

    private String userName;

    private String password;

    public String login() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findUser(userName, password);
        if (user == null) {
            return INPUT;
        } else {
            ActionContext context = ActionContext.getContext();
            Map<String, Object> session = context.getSession();
            session.put("user", user);
            return SUCCESS;
        }
    }

    public String logout() {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        session.clear();
        return LOGIN;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
