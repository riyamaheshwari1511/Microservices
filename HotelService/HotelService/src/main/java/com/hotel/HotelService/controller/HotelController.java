package com.hotel.HotelService.controller;

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
@PostMapping("/add")
    public ResponseEntity<Hotel> create (@RequestBody Hotel hotel){
        return  ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> get (@PathVariable String hotelId) {
        return ResponseEntity.ok().body(hotelService.get(hotelId));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Hotel>> getAllUser(){
        List<Hotel> allHotel = hotelService.getAll();
        return ResponseEntity.ok(allHotel);
    }

    }
