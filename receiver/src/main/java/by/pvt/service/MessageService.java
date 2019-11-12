package by.pvt.service;

import by.pvt.pojo.Message;
import by.pvt.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @CachePut(value = "message", key = "#message.idThing")
    public Message saveMessage(Message message) {

        Message lastMessage = messageRepository.findLastByIdThing(message.getIdThing());
        if (lastMessage == null) {
           return messageRepository.save(message);
        }

        if (!lastMessage.getBody().equals(message.getBody())) {
            return messageRepository.save(message);
        }
        return lastMessage;
    }


}
