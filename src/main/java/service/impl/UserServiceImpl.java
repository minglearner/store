package service.impl;

import constant.UserConstant;
import dao.UserDao;
import dao.impl.CategoryDaoImpl;
import model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import service.UserService;
import utils.BeanFactory;
import utils.MailUtils;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao = (UserDao) BeanFactory.getBean("UserDao");

    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    public void regist(@NotNull User user) {
        userDao.add(user);

        String message = "欢迎注册,<a href='http://localhost:8080/store/user?method=active&code=" + user.getCode() + "'>点此激活</a>";
        MailUtils.sendMail(user.getEmail(),message);
    }

    /**
     * 更新用户状态
     *
     * @param code
     * @return
     */
    @Override
    public User active(@NotNull String code) {
        User user = null;
        try {
            user = userDao.getByCode(code);
            if (user == null) {
                return user;
            } else {
                user.setState(UserConstant.USER_IS_ACTIVE);
                userDao.update(user);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public User login(@NotNull String userName, @NotNull String passWord) {
        return userDao.getByNameAndPwd(userName, passWord);
    }
}
