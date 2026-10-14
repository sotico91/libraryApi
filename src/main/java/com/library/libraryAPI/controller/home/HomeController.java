package com.library.libraryAPI.controller.home;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Hidden
@Controller
public class HomeController {

    // La raíz no tiene contenido propio: lleva a la documentación de la API
    @GetMapping("/")
    public String home() {
        return "redirect:/swagger-ui/index.html";
    }
}
