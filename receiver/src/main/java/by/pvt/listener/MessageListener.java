package by.pvt.listener;

import by.pvt.command.MessageCmd;
import by.pvt.pojo.Message;
import by.pvt.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;



@Component
public class MessageListener {

    @Autowired
    MessageService messageService;

    @JmsListener(destination = "message-queue", containerFactory = "jmsFactory")
    public void receiveMessage(MessageCmd messageCmd) {
        messageService.saveMessage(new Message(
                messageCmd.getIdThing(),
                messageCmd.getDate(),
                messageCmd.getBody(),
                false)
        );
    }

}
