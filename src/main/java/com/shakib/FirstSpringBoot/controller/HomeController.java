package com.shakib.FirstSpringBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = "text/plain; charset=UTF-8")
    public String home() {
        return """
                ✅ Welcome to Employee REST API
                
                ==============================
                Available Endpoints:
                ==============================
                
                ➤ POST Method        : /api/employees/create
                ➤ GET Method         : /api/employees/all
                ➤ GET By Specific Id : /api/employees/all/1
                ➤ PUT Method         : /api/employees/edit/1
                ➤ DELETE Method      : /api/employees/delete/1
                """;
    }
}
