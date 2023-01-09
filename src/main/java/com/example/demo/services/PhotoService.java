package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Photo;
import com.example.demo.repository.PhotoRepository;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	public List<Photo> getImagesByRestau(Long restaurantId) {
		return photoRepository.findAllByRestaurantId(restaurantId);
	}
	

}
