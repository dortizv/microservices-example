package com.tutorial.usuarioservice.controller;

import com.tutorial.usuarioservice.entity.Usuario;
import com.tutorial.usuarioservice.model.Bike;
import com.tutorial.usuarioservice.model.Car;
import com.tutorial.usuarioservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = usuarioService.getAll();
        if(usuarios.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping()
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        Usuario usuarioNew = usuarioService.save(usuario);
        return ResponseEntity.ok(usuarioNew);
    }

    @GetMapping("/cars/{usuarioId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("usuarioId") int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if(usuario == null)
            return ResponseEntity.notFound().build();
        List<Car> cars = usuarioService.getCars(usuarioId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{usuarioId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("usuarioId") int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if(usuario == null)
            return ResponseEntity.notFound().build();
        List<Bike> bikes = usuarioService.getBikes(usuarioId);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("/savecar/{usuarioId}")
    public ResponseEntity<Car> saveCar(@PathVariable("usuarioId") int usuarioId, @RequestBody Car car){
        if (usuarioService.getUsuarioById(usuarioId) == null)
            return ResponseEntity.notFound().build();
        Car carNew = usuarioService.saveCar(usuarioId, car);
        return ResponseEntity.ok(car);
    }
    @PostMapping("/savebike/{usuarioId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("usuarioId") int usuarioId, @RequestBody Bike bike){
        if (usuarioService.getUsuarioById(usuarioId) == null)
            return ResponseEntity.notFound().build();
        Bike bikeNew = usuarioService.saveBike(usuarioId, bike);
        return ResponseEntity.ok(bike);
    }

    @GetMapping("/getall/{usuarioId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("usuarioId") int usuarioId){
        Map<String, Object> result = usuarioService.getUserAndVehicles(usuarioId);
        return ResponseEntity.ok(result);
    }
}
