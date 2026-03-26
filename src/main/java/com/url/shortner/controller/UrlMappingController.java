package com.url.shortner.controller;

import com.url.shortner.dto.UrlMappingDTO;
import com.url.shortner.models.User;
import com.url.shortner.service.UrlMappingService;
import com.url.shortner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {
    private UrlMappingService urlMappingService;
    private UserService userService;

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDTO> createShortUrl(@RequestBody Map<String, String> request, Principal principal){
        String originalURL = request.get("originalURL");
        User user = userService.findByUsername(principal.getName());
        //Calling Service
        UrlMappingDTO urlMappingDTO = urlMappingService.createShortUrl(originalURL, user);
        return ResponseEntity.ok(urlMappingDTO);
    }

    @GetMapping("/myUrls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDTO>> getUserShortUrl(Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<UrlMappingDTO> url = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(url);
    }
}
