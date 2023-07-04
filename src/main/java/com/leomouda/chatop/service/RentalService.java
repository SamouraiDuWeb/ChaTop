package com.leomouda.chatop.service;

import com.leomouda.chatop.dto.RentalRequestDTO;
import com.leomouda.chatop.dto.RentalResponseDTO;
import com.leomouda.chatop.models.Rental;
import com.leomouda.chatop.models.User;
import com.leomouda.chatop.repositories.RentalRepository;
import com.leomouda.chatop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Autowired
    private UserRepository userRepository;

    public Rental getRentalById(Integer id) {
        return rentalRepository
                .findById(id)
                .orElseThrow(() -> null);
    }

    public List<RentalResponseDTO> getAllRentals() {
        List<RentalResponseDTO> rentalsDto = new ArrayList<>();
        List<Rental> rentals = new ArrayList<>(rentalRepository.findAll());
        rentals.forEach(rental -> rentalsDto.add(mapRentalEntityToDTO(rental)));
        return rentalsDto;
    }

    public RentalResponseDTO create(RentalRequestDTO newRental, String fileUrl) {
        Rental newRentalEntity = toEntity(newRental, fileUrl);
        Rental rentalCreated = rentalRepository.save(newRentalEntity);

        return  mapRentalEntityToDTO(rentalCreated);
    }

    public RentalResponseDTO update(RentalRequestDTO rentalUpdate, Integer id) {
        User owner = userRepository.findById(Math.toIntExact(rentalUpdate.getOwnerid()))
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return rentalRepository
                .findById(id)
                .map(rental -> {
                    rental.setName(rentalUpdate.getName());
                    rental.setDescription(rentalUpdate.getDescription());
                    rental.setPrice(rentalUpdate.getPrice());
                    rental.setSurface(rentalUpdate.getSurface());
                    rental.setOwner(owner);
                    return mapRentalEntityToDTO(rentalRepository.save(rental));
                })
                .orElseThrow(() -> new RuntimeException("Location non trouvé"));
    }

    public RentalResponseDTO mapRentalEntityToDTO(Rental rentalToMap){
        return new RentalResponseDTO(
                rentalToMap.getId(),
                rentalToMap.getName(),
                rentalToMap.getSurface(),
                rentalToMap.getPrice(),
                rentalToMap.getDescription(),
                rentalToMap.getPicture(),
                rentalToMap.getOwner().getId()
        );
    }

    public Rental toEntity(RentalRequestDTO dto, String fileUrl) {

        User owner = userRepository.findById(Math.toIntExact(dto.getOwnerid()))
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return Rental.builder()
                .owner(owner)
                .name(dto.getName())
                .description(dto.getDescription())
                .surface(dto.getSurface())
                .price(dto.getPrice())
                .picture(fileUrl)
                .build();
    }
}
