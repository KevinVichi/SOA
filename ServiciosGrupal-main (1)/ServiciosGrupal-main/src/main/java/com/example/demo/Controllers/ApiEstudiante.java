package com.example.demo.Controllers;


import com.example.demo.model.Estudiante;
import com.example.demo.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("rest")
public class ApiEstudiante {
    @Autowired
    EstudianteRepository estudianteRepository;
    
    @GetMapping("/all")
    public List<Estudiante> getEstudiante(){
        return estudianteRepository.findAll();
    }

    @PostMapping("/save/{cedula}")
    public Estudiante addEstudiante(@PathVariable String cedula,@RequestBody Estudiante estudiante){
        estudiante.setCedula(cedula);
        return estudianteRepository.saveAndFlush(estudiante);
    }
    @PutMapping("/edit/{cedula}")
    public Estudiante editEstudiante(@PathVariable String cedula ,@RequestBody Estudiante estudiante){
        estudiante.setCedula(cedula);
        return estudianteRepository.saveAndFlush(estudiante);
    }
    @DeleteMapping("/delete/{cedula}")
    public void deleteEstudiante(@PathVariable String cedula){
        estudianteRepository.deleteById(cedula);
    }

     @GetMapping("/get/{cedula}")
    public ResponseEntity<Estudiante> getEstudianteByID(@PathVariable String cedula) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(cedula);
        if (estudianteOptional.isPresent()) {
            Estudiante estudiante = estudianteOptional.get();
            return ResponseEntity.ok(estudiante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
