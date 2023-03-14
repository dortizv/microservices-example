package com.tutorial.bikeservice.controller;


import com.tutorial.bikeservice.entity.Bike;
import com.tutorial.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService BikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> bikes = BikeService.getAll();
        if(bikes.isEmpty() )
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id){
        Bike Bike = BikeService.getBikeById(id);
        if(Bike == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Bike);

    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike Bike) {
        Bike bikeNew = BikeService.save(Bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = BikeService.byUserId(userId);
        return ResponseEntity.ok(bikes);

    }

}
