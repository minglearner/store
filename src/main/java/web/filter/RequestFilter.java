package web.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class RequestFilter extends HttpServletRequestWrapper {
    private static final Logger LOGGER = Logger.getLogger(RequestFilter.class);

    private HttpServletRequest request;
    private boolean flag = true;

    public RequestFilter(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        if (name == null || name.trim().length() == 0) {
            return null;
        }
        String[] values = getParameterValues(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        if (name == null || name.trim().length() == 0) {
            return null;
        }
        Map<String, String[]> map = getParameterMap();
        if(map ==null || map.size() == 0){
            return null;
        }
        return map.get(name);
    }

    public Map<String, String[]> getParameterMap() {
        String method = request.getMethod();
        if (StringUtils.equalsIgnoreCase("post", method)) {
            try {
                request.setCharacterEncoding("utf-8");
                return request.getParameterMap();
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("Unsupported utf-8:" + e.getMessage(), e);
            }
        } else if (StringUtils.equalsIgnoreCase("get", method)) {
            Map<String, String[]> map = request.getParameterMap();
            if (flag) {
                for (String key : map.keySet()) {
                    String[] values = map.get(key);
                    for (int i = 0; i < values.length; i++) {
                        try {
                            values[i] = new String(values[i].getBytes("iso8859-1"), "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            LOGGER.error("not transfer to utf-8:" + e.getMessage(), e);
                        }
                    }
                }
                flag = false;
            }
            return map;
        }
        return super.getParameterMap();
    }
}
