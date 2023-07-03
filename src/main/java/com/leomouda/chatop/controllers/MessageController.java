package com.leomouda.chatop.controllers;

import com.leomouda.chatop.dto.MessageRequestDTO;
import com.leomouda.chatop.dto.MessageResponseDTO;
import com.leomouda.chatop.models.Message;
import com.leomouda.chatop.models.Rental;
import com.leomouda.chatop.models.User;
import com.leomouda.chatop.service.MessageService;
import com.leomouda.chatop.service.RentalService;
import com.leomouda.chatop.service.UserService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/messages")
@RequiredArgsConstructor
public class MessageController {


    private final MessageService messageService;

    private final RentalService rentalService;

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<MessageResponseDTO> addNewMessage(
            @RequestBody MessageRequestDTO newMessageDTO) throws IOException {
        User user =  userService.getUserById(newMessageDTO.getUser_id());
        Rental rental = rentalService.getRentalById(newMessageDTO.getRental_id());
        Message newMessage = new Message();
        newMessage.setMessage(newMessageDTO.getMessage());
        newMessage.setUser(user);
        newMessage.setRental(rental);
        return ResponseEntity.ok(messageService.save(newMessage));
    }
}
