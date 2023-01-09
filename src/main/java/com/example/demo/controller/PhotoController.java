package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Photo;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.services.PhotoService;
import com.example.demo.services.RestaurantService;

@Controller
@RequestMapping("/owner/")
public class PhotoController {
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping("/addImages/{id}")
	public String save(@RequestParam("photos") MultipartFile[] photos,@PathVariable(name = "id") Long id){
		restaurantService.addImages(photos, id);
		return "redirect:/owner/index";
	}
	
}
