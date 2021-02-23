package com.gmail.sbal.stels.controllers;

import com.gmail.sbal.stels.models.Chat;
import com.gmail.sbal.stels.models.Message;
import com.gmail.sbal.stels.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alex Balashov
 */
@Controller
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{chat_name}")
    public String show(@PathVariable("chat_name") String chatName, Model model) {
        Chat chat = chatService.getChat(chatName);
        model.addAttribute("chat", chat);
        return "chat";
    }

    @PatchMapping("/{chat_name}")
    public String edit(@PathVariable(value = "chat_name") String chatName,
                       @RequestParam(value = "chat_name") String newChatName,
                      @RequestParam String title,
                      Model model) {
        Chat chat = chatService.getChat(chatName);
        if(chat != null){
            chat.setChatName(chatName);
            chat.setTitle(title);
        }
        return "chat";
    }

    @PostMapping("/{chat_name}/add")
    public String addMessage(@PathVariable(value = "chat_name") String chatName,
                      @RequestParam String text,
                      Model model) {
        Chat chat = chatService.getChat(chatName);
        if (chat != null) {
            chatService.addMessage(new Message(text, chat));
            return "chat";
        }
        return "chat";
    }

    @GetMapping("/new")
    public String add() {
        return "chat_form";
    }

    @PostMapping
    public String add(@RequestParam(value = "chat_name") String chatName,
                    @RequestParam String title,
                    Model model) {
        if (chatService.getChat(chatName) == null) {
            Chat chat = new Chat(chatName, title);
            model.addAttribute("chat", chat);
            return "chat";
        }
        else return "chat_form";
    }
}
