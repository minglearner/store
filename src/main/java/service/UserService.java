package service;

import model.User;

public interface UserService {

    void regist(User user);

    User active(String code);

    User login(String userName,String passWord);
}
