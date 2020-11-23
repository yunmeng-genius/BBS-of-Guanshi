package finalproject.demo.controller;


import finalproject.demo.entity.Log;
import finalproject.demo.entity.WebPrint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.security.x509.AlgorithmId;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebEndpoint;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class indexController {


    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");
        ArrayList<Log> logs=WebPrint.getNewestLogs(username);
        model.addAttribute("logs", logs);
        return "index";
    }

    @RequestMapping("/personalIndex")
    public String personalIndex(HttpServletRequest request,Model model){
        String username=(String) request.getSession().getAttribute("username");
        ArrayList<Log> logs=WebPrint.getMyNewLogs(username);
        model.addAttribute("logs",logs);
        return "index";
    }

    @PostMapping("/postComment")
    @ResponseBody
    public boolean post(@RequestBody Map<String,String> comment, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        String content=comment.get("comment");
        int id = Integer.parseInt(comment.get("logId"));
        WebPrint.comment(content,username,id);
        return true;
    }

    @PostMapping("/postLog")
    public String postLog(@RequestParam String log, HttpServletRequest request){
        WebPrint.releaseLog(log,(String)request.getSession().getAttribute("username"));
        return "redirect:/index";
    }

    @PostMapping("/praise")
    @ResponseBody
    public boolean praise(@RequestBody Map<String,String> log,HttpServletRequest request){
        String logId=log.get("logId");
        String username = (String) request.getSession().getAttribute("username");
        WebPrint.like(username,Integer.parseInt(logId));
        return true;
    }
}
