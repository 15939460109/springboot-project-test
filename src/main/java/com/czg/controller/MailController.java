package com.czg.controller;

import com.czg.handler.MailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailController {

    @Autowired
    private MailHandler mailHandler;

    @GetMapping("/async")
    public String async() {

        try {
            mailHandler.sendByTemplate();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "Success";
    }
}
