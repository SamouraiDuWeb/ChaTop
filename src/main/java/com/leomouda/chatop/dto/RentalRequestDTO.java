package com.leomouda.chatop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequestDTO {
    private Integer id;
    private String name;
    private Double price;
    private Double surface;
    private MultipartFile picture;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
}
