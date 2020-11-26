package finalproject.demo.controller;


import finalproject.demo.entity.Log;
import finalproject.demo.entity.WebPrint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class indexController {


    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");       //从session中取出username
        ArrayList<Log> logs=WebPrint.getNewestLogs(username);                              //从数据库中查询出所有用户的log内容
        model.addAttribute("logs", logs);                                               //将log内容数组放进model，传递给前端
        return "index";                                                                    //返回"index.html"视图
    }

    @RequestMapping("/personalIndex")
    public String personalIndex(HttpServletRequest request,Model model){
        String username=(String) request.getSession().getAttribute("username");
        ArrayList<Log> logs=WebPrint.getMyNewLogs(username);                               //从数据库中查询用户的所有个人log
        model.addAttribute("logs",logs);
        return "index";
    }

    @PostMapping("/postComment")
    @ResponseBody
    public boolean post(@RequestBody Map<String,String> comment, HttpServletRequest request) {  //使用@RequestBody获取前端ajax请求json内容
        String username = (String) request.getSession().getAttribute("username");
        String content=comment.get("comment");
        int id = Integer.parseInt(comment.get("logId"));
        WebPrint.comment(content,username,id);                                                  //将评论人及对应博客的ID存进数据库
        return true;
    }

    @PostMapping("/postLog")
    public String postLog(@RequestParam String log, HttpServletRequest request){
        WebPrint.releaseLog(log,(String)request.getSession().getAttribute("username"));      //将发log的人的信息及log内容存进数据库
        return "redirect:/index";                                                               //重定向至"/index",进行log内容回显
    }

    @PostMapping("/praise")
    @ResponseBody
    public boolean praise(@RequestBody Map<String,String> log,HttpServletRequest request){      //利用@RequestBody获取前端传来的包含被点赞logId和点赞人的json信息
        String logId=log.get("logId");
        String username = (String) request.getSession().getAttribute("username");
        WebPrint.like(username,Integer.parseInt(logId));                                        //将点赞信息存入数据库
        return true;
    }
}
