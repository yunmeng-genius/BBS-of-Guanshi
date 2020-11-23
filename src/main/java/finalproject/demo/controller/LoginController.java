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


    @RequestMapping("/pwd")
    public String pwd(){
        return "register";
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



    @RequestMapping("/changePwd")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response,HttpServletRequest request) throws IOException {
        WebPrint.alterPassword(password,username);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script type='text/javascript'>alert('修改成功!请重新登录');</script>");
        request.getSession().getAttribute("username");
        return "login";
    }


    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return "login";
    }


}

