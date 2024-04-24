package com.UserService.Secuirty.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserControllerSecuirty {
    @GetMapping
    public ResponseEntity<String> SayHello(){
        return ResponseEntity.ok("Hello from user");
    }
}
