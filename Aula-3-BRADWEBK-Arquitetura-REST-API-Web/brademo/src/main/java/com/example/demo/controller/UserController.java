package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API em funcionamento!!");
    }

    @GetMapping("/{login}")
    public ResponseEntity<?> getUser(@PathVariable String login) {

        User user = userService.find(login);

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(404).body("Este usuário foi não encontrado.");
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {

        userService.add(user);

        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/{login}")
    public ResponseEntity<String> deleteUser(@PathVariable String login) {

        boolean removed = userService.remove(login);

        if (removed) {
            return ResponseEntity.ok("O usuário foi removido com sucesso!");
        }

        return ResponseEntity.status(404).body("Usuário não encontrado.");
    }
}