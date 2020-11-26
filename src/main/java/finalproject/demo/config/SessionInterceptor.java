package finalproject.demo.config;


import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    //重写方法，在路由处理前进行判断，实现拦截和放行
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {


        //任何人都可以访问登录页面及发起登录判断
        if ("/ensure".equals(arg0.getRequestURI()) || "/".equals(arg0.getRequestURI())) {
            return true;
        }

        //从session中拿出username，如果不存在，就重定向至根目录，重新登录；
        Object object = arg0.getSession().getAttribute("username");
        if (null == object) {
            arg1.sendRedirect("/");
            return false;
        }
        return true;
    }
}

