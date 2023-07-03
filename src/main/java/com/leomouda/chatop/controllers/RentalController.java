package com.leomouda.chatop.controllers;

import com.leomouda.chatop.dto.RentalRequestDTO;
import com.leomouda.chatop.dto.RentalResponseDTO;
import com.leomouda.chatop.dto.UserResponseDTO;
import com.leomouda.chatop.service.AuthService;
import com.leomouda.chatop.service.FilesStorageService;
import com.leomouda.chatop.service.RentalService;
import io.jsonwebtoken.io.IOException;
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

    @PostMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> create(@ModelAttribute("rental") RentalRequestDTO rentalRequestDTO, HttpServletRequest request) throws IOException, java.io.IOException, ServletException {

        System.out.println("///" + rentalRequestDTO.getName());

        if (!rentalRequestDTO.getPicture().isEmpty()) {
            UserResponseDTO currentUser = authService.getCurrentUser(request);
            rentalRequestDTO.setOwnerid(currentUser.getId());
            String fileUrl = filesStorageService.save(rentalRequestDTO.getPicture());



            return ResponseEntity.ok(rentalService.create(rentalRequestDTO,fileUrl));
        }
        throw new IOException("fail");
    }

    @GetMapping
    public ResponseEntity<List<RentalResponseDTO>> getAll() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> getOne(
            @PathVariable Integer id) {
        return ResponseEntity.ok(rentalService.mapRentalEntityToDTO(rentalService.getRentalById(id)));
    }

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