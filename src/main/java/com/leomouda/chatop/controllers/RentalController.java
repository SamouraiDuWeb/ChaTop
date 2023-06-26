package com.leomouda.chatop.controllers;

import com.leomouda.chatop.models.Rental;
import com.leomouda.chatop.models.User;
import com.leomouda.chatop.service.AuthService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

//    @PostMapping
//    public ResponseEntity<String> create(
//            @RequestParam("picture") MultipartFile picture,
//            @RequestParam("name") String name,
//            @RequestParam("surface") double surface ,
//            @RequestParam("price") double price,
//            @RequestParam("description") String description
//    ) throws IOException {
//        if (picture.getContentType() != null && !picture.getContentType().startsWith("image/")) {
//            throw  new IOException("Only images are allowed");
//        }
//        String fileName = filesStorageService.save(picture);
//        var owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        var rental = new Rental(name, surface, price, description, fileName, owner);
//        rentalService.save(rental);
//
//        return ResponseEntity.ok(new String("Rental created"));
//    }
}