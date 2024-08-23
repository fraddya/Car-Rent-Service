package com.unreallabss.carrent.dto.request.brand;


import com.unreallabss.carrent.enums.Status;
import lombok.Data;

@Data
public class BrandCreateRequest {
    private String name;
    private String code;
    private String description;
    private Status status;
}
