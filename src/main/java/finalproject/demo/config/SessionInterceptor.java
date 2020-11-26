package finalproject.demo.config;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {
//    @Override
//    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) {
//    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {

        Object object = arg0.getSession().getAttribute("username");
        if ("/ensure".equals(arg0.getRequestURI()) || "/".equals(arg0.getRequestURI())) {
            if (object != null) {
                arg1.sendRedirect("/index");
            }
            return true;
        }


        if (null == object) {
            arg1.sendRedirect("/");
            return false;
        }
        return true;
    }
}

