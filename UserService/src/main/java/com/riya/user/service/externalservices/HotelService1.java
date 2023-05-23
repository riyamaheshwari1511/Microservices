package com.riya.user.service.externalservices;

import com.riya.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "HOTEL-SERVICE" , url = "http://localhost:9091")
public interface HotelService1 {
@GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

   // Hotel getHotel();
}
