package com.gmail.sbal.stels.controllers;

import com.gmail.sbal.stels.models.User;
import com.gmail.sbal.stels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alex Balashov
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam(value = "nick_name") String nickName,
                             @RequestParam String email) {
        User user = userService.get(nickName);
        if(user != null && user.getEmail().equals(email)) {
            return "redirect:/users/" + nickName + "/chats";
        }
        else return "redirect:/";
    }

    @PostMapping("/user")
    public String userAdd(@RequestParam(value = "nick_name") String nickName,
                             @RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String bio) {
        User user = userService.get(nickName);
        if(user == null){
            user = new User(nickName, name, email, bio);
        }
        if(user.getEmail().equals(email)) {
            return "redirect:/users/" + nickName + "/chats";
        }
        return "redirect:/";
    }

    @GetMapping("/user/{nick_name}")
    public String show(@PathVariable("nick_name") String nickName, Model model) {
        User user = userService.get(nickName);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/user/{nick_name}/chats")
    public String chats(@PathVariable("nick_name") String nickName, Model model) {
        User user = userService.get(nickName);
        model.addAttribute("chats", userService.getChatsOfUser(user));
        return "chats";
    }

    @PatchMapping("/user/{nick_name}")
    public String userEdit(@PathVariable("nick_name") String nickName,
                          @RequestParam(value = "nick_name") String newNick,
                          @RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String bio) {
        User user = userService.get(nickName);
        user.setNickName(newNick);
        user.setName(name);
        user.setEmail(email);
        user.setBio(bio);
        return "redirect:/user" + nickName;
    }

    @DeleteMapping("/user/{nick_name}")
    public String userDelete(@PathVariable("nick_name") String nickName) {
        userService.delete(userService.get(nickName));
        return "redirect:/";
    }
}
