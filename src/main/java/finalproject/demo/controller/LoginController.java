package finalproject.demo.controller;


import finalproject.demo.entity.WebPrint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class LoginController {


    @RequestMapping("/")
    public String login() {
        return "login";
    }             //网页根目录返回"login.html"视图


    @RequestMapping("/pwd")
    public String pwd(){
        return "register";                                //返回修改密码视图
    }

    @RequestMapping("/ensure")
    public String ensure(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response, HttpServletRequest request) throws IOException {
        boolean flag =WebPrint.logJudge(username,password);                   //判断前端表单提交的username和password是否存在于数据库且一致
        if(flag){                                                             //如果该用户存在且密码正确
            request.getSession().setAttribute("username",username);        //往session中添加username字段，并将已登录的用户username放入session
            return "redirect:/index";                                         //重定向至"/index"
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script type='text/javascript'>alert('用户名或密码错误!');</script>");     //如果密码不正确就在前端提醒alert用户名或密码错误
        return "login";                                                                    //重新返回登录视图
    }



    @RequestMapping("/changePwd")
    public String register(@RequestParam("password") String password, HttpServletResponse response,HttpServletRequest request) throws IOException {
        String username=(String) request.getSession().getAttribute("username");
        WebPrint.alterPassword(password,username);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script type='text/javascript'>alert('修改成功!请重新登录');</script>");
        request.getSession().getAttribute("username");
        return "login";
    }


    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("username");                          //退出时，从session中将username移出，并返回登录
        return "login";
    }


}

