package com.hotel.HotelService.implementation;

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.exception.ResourceNotFoundException;
import com.hotel.HotelService.repositories.HotelRepositories;
import com.hotel.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
   @Autowired
   private HotelRepositories hotelRepositories;


    @Override
    public Hotel create(Hotel hotel) {
      String randomHotelId =  UUID.randomUUID().toString();
      hotel.setHotelId(randomHotelId);
        return hotelRepositories.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepositories.findAll();
    }

    @Override
    public Hotel get(String hotelId) {
        return hotelRepositories.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("hotel with given id is not found!!!" + hotelId));

    }
}
