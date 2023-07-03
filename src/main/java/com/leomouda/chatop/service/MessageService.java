package com.leomouda.chatop.service;

import com.leomouda.chatop.dto.MessageResponseDTO;
import com.leomouda.chatop.models.Message;
import com.leomouda.chatop.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public MessageResponseDTO save(Message message){

        Message messageCreated = messageRepository.save(message);

        return new MessageResponseDTO(messageCreated.getMessage());
    }
}
