package finalproject.demo.controller;


import finalproject.demo.entity.Comment;
import finalproject.demo.entity.Log;
import finalproject.demo.entity.WebPrint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class indexController {


    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");
        ArrayList<Log> logs=WebPrint.getNewestLogs();
        model.addAttribute("logs", logs);
        model.addAttribute("comment", new Comment());
        return "index";
    }

    @RequestMapping("/postComment")
    @ResponseBody
    public String post(@RequestBody Map<String,String> comment, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        String content=comment.get("comment");
        String id = comment.get("logId");
        System.out.println(content+id);
        comment.remove("logId");
        comment.put("username",username);
        return comment.toString();
    }
}
