package utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 获取数据事务
 */
public class DataSourceUtils {
    private static final Logger LOGGER = Logger.getLogger(DataSourceUtils.class);
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    //获取数据源
    public static DataSource getDataSource() {
        return dataSource;
    }

    //获取连接
    public static Connection getConnection() {
        Connection connection = tl.get();
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                tl.set(connection);
                return connection;
            } catch (SQLException se) {
                LOGGER.error("get connection error" + se.getMessage(), se);
            }
        }
        return null;
    }

    //释放资源
    public static void closeResource(Statement sm, ResultSet rs){
         closeStatement(sm);
         closeResultSet(rs);
    }

    //释放资源
    public static void closeResource(Statement sm, ResultSet rs, Connection cn){
        closeResource(sm, rs);
        closeConnection(cn);
    }

    //关闭Statement
    public static void closeStatement(Statement sm){
        if(sm != null){
            try{
                sm.close();
            }catch (SQLException se){
                LOGGER.error("close Statement error"+se.getMessage(),se);
            }finally {
                sm = null;
            }
        }
    }

    //关闭结果集
    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }catch (SQLException se){
                LOGGER.error("close ResultSet error"+se.getMessage(),se);
            }finally {
                rs = null;
            }
        }
    }

    //关闭连接
    public static void closeConnection(Connection cn){
        if(cn != null){
            try {
                cn.close();
            } catch (SQLException se) {
                LOGGER.error("close Connection error"+se.getMessage(),se);
            }finally {
                cn = null;
            }
        }
    }

    //开启事务
    public static void startTransaction(){
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("start Transaction error"+e.getMessage(),e);
        }
    }

    //事务成功
    public static void commitAndClose(){
        Connection cn = null;
        try {
            cn = getConnection();
            cn.commit();
            cn.close();
            tl.remove();
        } catch (Exception e) {
            LOGGER.error("commit Transaction error"+e.getMessage(),e);
        }
    }

    //事务失败
    public static void rollbackAndClose(){
        Connection cn = null;
        try {
            cn = getConnection();
            cn.rollback();
            cn.close();
            tl.remove();
        } catch (Exception e) {
            LOGGER.error("rollback Transaction error"+e.getMessage(),e);
        }
    }
}
