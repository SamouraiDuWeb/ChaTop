package com.leomouda.chatop.service;



import com.leomouda.chatop.models.Rental;
import com.leomouda.chatop.repositories.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void save(Rental rental) {
        rentalRepository.save(rental);
    }

    public List<Rental> findByOwnerId(Long ownerId) {
        return rentalRepository.findAllByOwner_Id(ownerId);
    }

    public Optional<Rental> findById(Long id) {
        return rentalRepository.findById(id);
    }
}
