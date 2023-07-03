package com.leomouda.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponseDTO {
    public Integer id;

    public String name;

    public double surface;

    public double price;

    public String description;

    @JsonProperty("picture")
    public String picture;

    @JsonProperty("owner_id")
    public Integer ownerId;
}

