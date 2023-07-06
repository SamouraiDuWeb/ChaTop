package com.leomouda.chatop.controllers;

import com.leomouda.chatop.dto.RentalRequestDTO;
import com.leomouda.chatop.dto.RentalResponseDTO;
import com.leomouda.chatop.dto.UserResponseDTO;
import com.leomouda.chatop.service.AuthService;
import com.leomouda.chatop.service.FilesStorageService;
import com.leomouda.chatop.service.RentalService;
import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final FilesStorageService filesStorageService;
    private final AuthService authService;

    @Operation(summary = "Ajouter une nouvelle location",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne la location créée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requête - les informations fournies sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @PostMapping
    public ResponseEntity<RentalResponseDTO> create(@ModelAttribute("rental") RentalRequestDTO rentalRequestDTO, HttpServletRequest request) throws IOException, java.io.IOException, ServletException {
        if (!rentalRequestDTO.getPicture().isEmpty()) {
            UserResponseDTO currentUser = authService.getCurrentUser(request);
            rentalRequestDTO.setOwnerid(currentUser.getId());
            String fileUrl = filesStorageService.save(rentalRequestDTO.getPicture());

            return ResponseEntity.ok(rentalService.create(rentalRequestDTO,fileUrl));
        }
        throw new IOException("fail");
    }

    @Operation(summary = "Récupérer tous les locations",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne la liste de toutes les locations",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @GetMapping
    public ResponseEntity<List<RentalResponseDTO>> getAll() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }


    @Operation(summary = "Récupérer une location spécifique par son ID",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne la location demandée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Location non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> getOne(
            @PathVariable Integer id) {
        return ResponseEntity.ok(rentalService.mapRentalEntityToDTO(rentalService.getRentalById(id)));
    }

    @Operation(summary = "Mettre à jour une location existante",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne la location mise à jour",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requête - les informations fournies sont invalides"),
            @ApiResponse(responseCode = "404", description = "Location non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @PutMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> update(
            @ModelAttribute("rental") RentalRequestDTO rentalRequestDTO,
            @PathVariable Integer id,
            HttpServletRequest request
    ) throws IOException, ServletException {
        UserResponseDTO currentUser = authService.getCurrentUser(request);
        rentalRequestDTO.setOwnerid(currentUser.getId());
        return ResponseEntity.ok(rentalService.update(rentalRequestDTO, id));
    }
}