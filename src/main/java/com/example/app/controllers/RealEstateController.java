package com.example.app.controllers;

import com.example.app.entities.RealEstate;
import com.example.app.services.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RealEstateController {

    @Autowired
    private RealEstateService realEstateService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody RealEstate realEstate) {
        realEstateService.addRealEstate(realEstate);
        return new ResponseEntity<>("added", HttpStatus.CREATED);

    }

    @GetMapping("/getAll")
    public List<RealEstate> addGetAll() {
        return realEstateService.bla();
    }

    @GetMapping("/get")
    public List<RealEstate> get() {
        return realEstateService.getAll();
    }


    @GetMapping("/getSorted")
    public ResponseEntity<Map<Object, Long>> getSorted() {

        Map<Object, Long> map = realEstateService.getSorted();

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id, @RequestHeader HttpHeaders httpHeaders) {

        String language = httpHeaders.getFirst(HttpHeaders.ACCEPT_LANGUAGE);
        System.out.println("==>> language: " + language);
        realEstateService.delete(id);

        return new ResponseEntity<>("The object has been successfully deleted!", HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody RealEstate realEstate) {
        realEstateService.updateEntity(id, realEstate);
        return new ResponseEntity<>("RealEstate has been updated", HttpStatus.OK);

    }
}
