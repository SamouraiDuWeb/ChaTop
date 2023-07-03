package com.leomouda.chatop.mappers;

import com.leomouda.chatop.dto.RentalResponseDTO;
import com.leomouda.chatop.models.Rental;
import com.leomouda.chatop.service.FilesStorageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {
    private final FilesStorageService filesStorageService;

    public RentalMapper(FilesStorageService filesStorageService) {
        this.filesStorageService = filesStorageService;
    }

    public List<RentalResponseDTO> mapListToResponse(List<Rental> rentals) {
        return rentals.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public RentalResponseDTO mapToResponse(Rental rental) {
        var rentalResponseDTO = new RentalResponseDTO();
        var dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        BeanUtils.copyProperties(rental, rentalResponseDTO);
        rentalResponseDTO.ownerId = rental.getOwner().getId();
        return rentalResponseDTO;
    }
}
