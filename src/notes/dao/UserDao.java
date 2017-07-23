package notes.dao;

import notes.bean.User;

public interface UserDao {

	User findUser(int userId);

	User findUser(String userName, String password);

	boolean addUser(User user);
}
