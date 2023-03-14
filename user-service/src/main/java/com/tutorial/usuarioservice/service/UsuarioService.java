package com.tutorial.usuarioservice.service;

import com.tutorial.usuarioservice.entity.Usuario;
import com.tutorial.usuarioservice.feignclients.BikeFeignClient;
import com.tutorial.usuarioservice.feignclients.CarFeignClient;
import com.tutorial.usuarioservice.model.Bike;
import com.tutorial.usuarioservice.model.Car;
import com.tutorial.usuarioservice.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository UsuarioRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<Usuario> getAll() {
        return UsuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return UsuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        Usuario usuarioNew = UsuarioRepository.save(usuario);
        return usuarioNew;
    }

    public List<Car> getCars(int usuarioId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/"+usuarioId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int usuarioId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/"+usuarioId, List.class);
        return bikes;
    }
    public Car saveCar(int usuarioId, Car car){
        car.setUserId(usuarioId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(int usuarioId, Bike bike){
        bike.setUserId(usuarioId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int usuarioId){
        Map<String, Object> result = new HashMap<>();

        Usuario usuario = UsuarioRepository.findById(usuarioId).orElse(null);
        if(usuario == null) {
            result.put("Mensaje", "Usuario no encontrado");
            return result;
        }
        result.put("Usuario", usuario);

        List<Car> cars = carFeignClient.getCars(usuarioId);
        if(cars.isEmpty())
            result.put("Cars","Usuario no tiene carros");
        else
            result.put("Cars", cars);

        List<Bike> bikes = bikeFeignClient.getBikes(usuarioId);
        if(bikes.isEmpty())
            result.put("Bikes","Usuario no tiene motos");
        else
            result.put("Bikes", bikes);

        return result;
    }
}
