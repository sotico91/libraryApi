package com.library.libraryAPI.controller.home;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    private final HomeController homeController = new HomeController();

    @Test
    void testHomeRedirectsToSwagger() {
        assertEquals("redirect:/swagger-ui/index.html", homeController.home());
    }

}
