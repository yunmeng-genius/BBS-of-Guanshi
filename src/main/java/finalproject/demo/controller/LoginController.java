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
    }



    @RequestMapping("/ensure")
    public String ensure(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response, HttpServletRequest request) throws IOException {
        boolean flag =WebPrint.logJudge(username,password);
        if(flag){
            request.getSession().setAttribute("username",username);
            return "redirect:/index";
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script type='text/javascript'>alert('用户名或密码错误!');</script>");
        return "login";
    }



    @RequestMapping("/register")
    public String register(String username, String password, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script type='text/javascript'>alert('注册成功!');</script>");
        return "login";
    }


    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return "login";
    }


}

