package com.leomouda.chatop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponseDTO {
    private Integer owner_id;
    private String name;
    private Double price;
    private Double surface;
    private String picture;
    private String description;
}

