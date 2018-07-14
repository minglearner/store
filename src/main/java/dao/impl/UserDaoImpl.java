package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.log4j.Logger;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;
import utils.DataSourceUtils;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    /**
     * 添加新用户
     *
     * @param user
     * @throws Exception
     */
    public boolean add(User user){
        QueryRunner query = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
        boolean flag = true;
        try {
            query.update(sql, user.getUid(), user.getUsername(), user.getPassword(),
                    user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(),
                    user.getSex(), user.getState(), user.getCode());

        } catch (SQLException e) {
            LOGGER.error("add user error" + e.getMessage(), e);
            flag = false;
        }
        return flag;
    }

    /**
     * 获取指定用户
     *
     * @param code
     * @return
     * @throws Exception
     */
    public User getByCode(String code){
        QueryRunner query = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where code = ? limit 0,1";
        User user = null;
        try {
            user = query.query(sql, new BeanHandler<>(User.class), code);
        } catch (SQLException e) {
            LOGGER.error("get user error" + e.getMessage(), e);
        }
        return user;
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @throws Exception
     */
    public boolean update(User user){
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update user set username = ?,password = ?,name= ?,email= ?,telephone= ?," +
                "birthday = ?,sex = ?,state = ?,code = ? where uid = ?";
        boolean flag = true;
        try {
            qr.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
                    user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), null, user.getUid());
        } catch (SQLException e) {
            LOGGER.error("update user error" + e.getMessage(), e);
            flag = false;
        }
        return flag;
    }

    /**
     * 根据用户名密码获取用户
     *
     * @param userName
     * @param passWord
     * @return
     * @throws Exception
     */
    public User getByNameAndPwd(String userName, String passWord){
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where username = ? and password = ?";
        User user = null;
        try {
            user = qr.query(sql, new BeanHandler<>(User.class), userName, passWord);
        } catch (SQLException e) {
            LOGGER.error("username or password error" + e.getMessage(), e);
        }
        return user;
    }
}
