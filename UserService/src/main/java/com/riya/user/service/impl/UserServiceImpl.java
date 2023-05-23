package com.riya.user.service.impl;

import com.riya.user.service.entities.Hotel;
import com.riya.user.service.entities.Rating;
import com.riya.user.service.entities.User;
import com.riya.user.service.exceptions.ResourceNotFoundException;

import com.riya.user.service.externalservices.HotelService1;
import com.riya.user.service.repositories.UserRepository;
import com.riya.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService1 hotelService;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
       String randomUserId = UUID.randomUUID().toString();
       user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));

        Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:9111/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {

            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());

        user.setRating(ratingList);

        return user;
    }
}
