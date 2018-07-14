package dao;

import model.User;

public interface UserDao {

    boolean add(User user);

    User getByCode(String code);

    boolean update(User user);

    User getByNameAndPwd(String userName,String passWord);
}
