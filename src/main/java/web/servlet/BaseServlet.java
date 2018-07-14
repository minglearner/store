package web.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BaseServlet.class);

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        @SuppressWarnings("rawtypes")
        Class cls = this.getClass();
        String method = request.getParameter("method");
        if (method == null) {
            method = "index";
        }
        try {
            Method m = cls.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            String s = (String) m.invoke(this, request, response);
            if (m != null) {
                request.getRequestDispatcher(s).forward(request, response);
            }
        } catch (NoSuchMethodException e) {
            LOGGER.error("not find method:" + e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LOGGER.error("illegal invoke:" + e.getMessage(), e);
        } catch (InvocationTargetException e) {
            LOGGER.error("invocaton error:" + e.getMessage(), e);
        } catch (ServletException e) {
            LOGGER.error("Servlet error:" + e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error("IO error:" + e.getMessage(), e);
        }
    }

    public String index(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
