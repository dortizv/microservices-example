package com.tutorial.carservice.controller;


import com.tutorial.carservice.entity.Car;
import com.tutorial.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService CarService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = CarService.getAll();
        if(cars.isEmpty() )
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id){
        Car Car = CarService.getCarById(id);
        if(Car == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Car);

    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car Car) {
        Car carNew = CarService.save(Car);
        return ResponseEntity.ok(carNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId) {
        List<Car> cars = CarService.byUserId(userId);
        return ResponseEntity.ok(cars);

    }

}
