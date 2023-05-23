package com.hotel.HotelService.repositories;

import com.hotel.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepositories extends JpaRepository<Hotel , String> {

}
