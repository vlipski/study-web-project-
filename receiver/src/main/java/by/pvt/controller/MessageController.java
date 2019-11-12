package by.pvt.controller;

import by.pvt.command.MessageCmd;
import by.pvt.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;


@RestController
public class MessageController {



    @Autowired
    MessageService messageService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/order")
    public void saveMessage(@Valid @RequestBody MessageCmd messageCmd) {
        jmsTemplate.convertAndSend("message-queue", messageCmd);

    }


}
