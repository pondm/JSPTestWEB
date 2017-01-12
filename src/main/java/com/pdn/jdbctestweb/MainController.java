package com.pdn.jdbctestweb;

import com.pdn.jdbctestweb.dao.UserJDBCTemplate;
import com.pdn.jdbctestweb.model.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.json.simple.parser.JSONParser;

@Controller
public class MainController { 
    @Autowired
    DataSource dataSource;
    UserJDBCTemplate userJDBCTemplate;
    JSONParser parser;
    @Autowired
    private SimpMessagingTemplate template;
    
    List<User> users;
    
    @PostConstruct
    public void init() {
       parser = new JSONParser();
       userJDBCTemplate = new UserJDBCTemplate();
       userJDBCTemplate.setDataSource(dataSource);
       users = userJDBCTemplate.listUsers();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView users() {             
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }
    
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public void updateUser(@RequestBody String data) throws Exception {
        JSONObject obj = (JSONObject) parser.parse(data);
        User user = userJDBCTemplate.getUser(Integer.valueOf((String)(obj.get("id"))));
        user.setUsername((String)(obj.get("username")));
        userJDBCTemplate.update(user);
        users = userJDBCTemplate.listUsers();    
        sendPush("update");
    }
    
    public void sendPush(String msg) throws Exception {
        template.convertAndSend("/topic/update",msg);
    }
}
