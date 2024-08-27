package com.golf.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageDto {

    private Long id;
    private String name;
    private String base64Data;

    public ImageDto(Long id, String name, String base64Data) {
        this.id = id;
        this.name = name;
        this.base64Data = base64Data;
    }
}
