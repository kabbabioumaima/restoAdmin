package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Photo;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Serie;
import com.example.demo.model.Specialite;
import com.example.demo.model.Ville;
import com.example.demo.model.Zone;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.SpecialiteRepository;
import com.example.demo.repository.VilleRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.services.PhotoService;
import com.example.demo.services.RestaurantService;
import com.example.demo.services.SerieService;
import com.example.demo.services.SpecialiteService;
import com.example.demo.services.VilleService;
import com.example.demo.services.ZoneService;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/market/")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private VilleService villeService;
	
	@Autowired
	private VilleRepository villeRepository;
	
	@Autowired
	private SerieService serieService;
	
	@Autowired
	private SpecialiteService specialiteService;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired 
	private ZoneService zoneService;

	@GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getAllRestaus() {
		try {
			List<Restaurant> restaurants = new ArrayList<Restaurant>();
			restaurantService.getAll().forEach(restaurants::add);
			if (restaurants.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("---------------------------restaus");
			return ResponseEntity.ok(restaurants); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/images/{id}")
	public  ResponseEntity<List<Photo>>  show(@PathVariable("id")Long id) {
		List<Photo> images = photoService.getImagesByRestau(id);
		return ResponseEntity.ok(images);
	}
	
	@GetMapping(value="/series", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Serie>> getAllSeries() {
		try {
			List<Serie> series = new ArrayList<Serie>();
			serieService.getAll().forEach(series::add);
			if (series.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			}
			System.out.println("---------------------------series");
			return ResponseEntity.ok(series); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/specs", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Specialite>> getAllSpecs() {
		try {
			List<Specialite> specialites = new ArrayList<Specialite>();
			specialiteService.getAll().forEach(specialites::add);
			if (specialites.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			}
			System.out.println("---------------------------specs");
			return ResponseEntity.ok(specialites); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/villes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ville>> getAllVilles() {
		try {
			List<Ville> villes = new ArrayList<Ville>();
			villeService.getAll().forEach(villes::add);
			if (villes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			}
			System.out.println("---------------------------villes");
			return ResponseEntity.ok(villes); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/byVille/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getAllRestausByVille(@PathVariable("id") Long idVille) {
		try {
			List<Restaurant> restaurants = new ArrayList<Restaurant>();
			restaurantService.getAllByVille(villeRepository.getById(idVille)).forEach(restaurants::add);
			if (restaurants.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("---------------------------VILLE restaus");
			return ResponseEntity.ok(restaurants); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/zones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Zone>> getAllZonesByVille(@PathVariable("id") Long idVille) {
		try {
			List<Zone> zones = zoneService.getAllZonesByVille(idVille);
			if (zones.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("---------------------------VILLE zones");
			return ResponseEntity.ok(zones); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/restosBySerie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getAllRestosBySerie(@PathVariable("id") Long serie) {
		try {
			List<Restaurant> restos = restaurantService.getAllBySerie(serie);
			if (restos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("---------------------------RESTOS serie");
			return ResponseEntity.ok(restos); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/restosBySpec/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getAllRestosBySpec(@PathVariable("id") Long serie) {
		try {
			List<Restaurant> restos = restaurantService.getAllBySpecialite(serie);
			if (restos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("---------------------------RESTOS SPEC");
			return ResponseEntity.ok(restos); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
