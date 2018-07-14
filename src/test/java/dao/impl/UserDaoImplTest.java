package dao.impl;

import model.User;
import org.junit.Before;
import org.junit.Test;
import utils.DateUtils;
import utils.UUIDUtils;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    private User user = new User();
    private UserDaoImpl userDao = new UserDaoImpl();
    @Before
    public void setUp() throws Exception {
        user.setUid(UUIDUtils.getId());
        user.setUsername("张三");
        user.setPassword("admin");
        user.setName("admin");
        user.setEmail("123@qq.com");
        user.setTelephone("110");
        user.setBirthday(DateUtils.getDate("2018-07-10 23:20:06"));
        user.setSex("男");
        user.setState(1);
        user.setCode("001");
    }

    @Test
    public void add() throws Exception{
            assertTrue(userDao.add(user));
    }

    @Test
    public void getByCode() throws Exception{
    }

    @Test
    public void update() throws Exception{
    }

    @Test
    public void getByNameAndPwd() throws Exception{
    }
}