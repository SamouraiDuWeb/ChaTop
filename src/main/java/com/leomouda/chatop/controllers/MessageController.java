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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


    @Operation(summary = "Ajouter un nouveau message",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne le message créé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requête - les informations fournies sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
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
