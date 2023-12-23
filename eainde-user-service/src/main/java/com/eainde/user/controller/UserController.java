package com.eainde.user.controller;

import com.eainde.user.entity.User;
import com.eainde.user.service.UserService;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    UserController(final UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<User> findById(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Void> add(@RequestBody User user) {
        service.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<User> update(@RequestBody User user) {
        return new ResponseEntity<>(service.update(user), HttpStatus.OK);
    }
}
