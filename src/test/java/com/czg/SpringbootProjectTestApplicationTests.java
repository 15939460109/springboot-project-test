package com.czg;

import com.czg.handler.MailHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
class SpringbootProjectTestApplicationTests {

    @Autowired
    private MailHandler mailHandler;

    @Test
    void contextLoads() {
        // mailHandler.send();

        try {
            mailHandler.sendByTemplate();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
