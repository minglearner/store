package web.servlet;

import constant.UserConstant;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import service.UserService;
import utils.BeanFactory;
import utils.MD5Utils;
import utils.UUIDUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;



public class UserServlet extends BaseServlet {
    private static final Logger LOGGER = Logger.getLogger(UserServlet.class);
    private UserService userService = (UserService) BeanFactory.getBean("UserService");

    /**
     * 跳转到注册页面
     * @return
     */
    public String registUi() {
        return "/jsp/register.jsp";
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @return
     */
    public String regist(HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        try {
            BeanUtils.populate(user,request.getParameterMap());
            user.setUid(UUIDUtils.getId());
            user.setCode(UUIDUtils.getId());
            String psw = MD5Utils.md5(user.getPassword());
            userService.regist(user);
            request.setAttribute("msg","用户注册已经成功，请去邮箱激活");
            return "/jsp/msg.jsp";
        } catch (IllegalAccessException e) {
            LOGGER.error("illegal access:"+e.getMessage(),e);
        } catch (InvocationTargetException e) {
            LOGGER.error("invocation error:"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用户激活
     * @param request
     * @param response
     * @return
     */
    public String active(HttpServletRequest request,HttpServletResponse response){
        String code = request.getParameter("code");
        User user = userService.active(code);
        if(user == null){
            request.setAttribute("msg","请重新激活");
        }else{
            request.setAttribute("msg","激活成功");
        }
        return "/jsp/msg.jsp";
    }

    /**
     * 跳转到登录页面
     * @param request
     * @param response
     * @return
     */
    public String loginUi(HttpServletRequest request,HttpServletResponse response){
          return "/jsp/login.jsp";
    }

    /**
     *用户登录
     * @param request
     * @param response
     * @return
     */
    public String login(HttpServletRequest request,HttpServletResponse response){
           String username = request.getParameter("username");
           String password = MD5Utils.md5(request.getParameter("password"));

           User user = userService.login(username,password);
           if(user == null){
               request.setAttribute("msg","用户名或密码错误");
               return loginUi(request,response);
           }else{
               if(UserConstant.USER_IS_ACTIVE != user.getState()){
                   request.setAttribute("msg","请先激活账号");
                   return loginUi(request,response);
               }
           }

           request.getSession().setAttribute("user",user);
        try {
            response.sendRedirect(request.getContextPath()+"/");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 登出
     * @param request
     * @param response
     * @return
     */
    public String logOut(HttpServletRequest request,HttpServletResponse response){
        request.getSession().invalidate();
        try {
            response.sendRedirect(request.getContextPath());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return null;
    }
}
