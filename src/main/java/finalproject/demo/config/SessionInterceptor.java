package finalproject.demo.config;


import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {


        if ("/ensure".equals(arg0.getRequestURI()) || "/".equals(arg0.getRequestURI())) {
            return true;
        }

        Object object = arg0.getSession().getAttribute("username");
        if (null == object) {
            arg1.sendRedirect("/");
            return false;
        }
        return true;
    }
}

