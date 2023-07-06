package com.leomouda.chatop.controllers;

import com.leomouda.chatop.dto.UserResponseDTO;
import com.leomouda.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @Operation(
            summary = "Récupérer un utilisateur par son ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne l'utilisateur demandé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(userService.mapUserToDTO(userService.getUserById(id)));
    }
}
