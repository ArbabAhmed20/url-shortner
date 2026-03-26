package com.url.shortner.dto;

import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlMappingDTO {

    @Id
    private Long id;

    private String originalURL;
    private String shortURL;
    private int clickCount;
    private LocalDateTime createdDate;
    private String username;
}
