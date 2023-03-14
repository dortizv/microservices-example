package com.tutorial.usuarioservice.feignclients;

import com.tutorial.usuarioservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="car-service", url="http://localhost:8002", path = "/car")
//@RequestMapping("/car") REQUESTMAPPING NOT ALLOWED ON FEIGN INTERFACES
public interface CarFeignClient {
    @PostMapping()
    Car save(@RequestBody Car car);

    @GetMapping("byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") int usuarioId);

}
