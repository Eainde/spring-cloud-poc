package com.eainde.order.controller;

import com.eainde.order.entity.Order;
import com.eainde.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    OrderController(final OrderService service){
        this.service=service;
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<Order> findById(@PathVariable int id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(value="/", produces = "application/json")
    public ResponseEntity<List<Order>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value="/", produces = "application/json")
    public ResponseEntity<Void> add(@RequestBody Order order){
        service.add(order);
    return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value="/", produces = "application/json")
    public ResponseEntity<Order> update(@RequestBody Order order){
        return new ResponseEntity<>(service.update(order), HttpStatus.OK);
    }
}
