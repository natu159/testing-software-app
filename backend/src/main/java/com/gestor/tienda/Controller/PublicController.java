package com.gestor.tienda.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    @GetMapping("/home")
    public String home() {
        return "Public Home";
    }
}