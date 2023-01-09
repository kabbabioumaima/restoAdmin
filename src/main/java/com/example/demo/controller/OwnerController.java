package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.RestaurantDto;
import com.example.demo.model.Etat;
import com.example.demo.model.User;
import com.example.demo.model.Zone;
import com.example.demo.repository.EtatRepository;
import com.example.demo.services.RestaurantService;
import com.example.demo.services.SerieService;
import com.example.demo.services.SpecialiteService;
import com.example.demo.services.UserService;
import com.example.demo.services.UserServiceImpl;
import com.example.demo.services.VilleService;
import com.example.demo.services.ZoneService;
import com.example.demo.utils.EtatEnum;

@Controller
@RequestMapping("/owner/")
public class OwnerController {
	
	@Autowired
	private UserServiceImpl userImpl;

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ZoneService zoneService;

	@Autowired
	private VilleService villeService ;
	
	@Autowired
	private SpecialiteService specialiteService;
	
	@Autowired
	private SerieService serieService;
	
	@GetMapping(value="/index")
    public String ownerView(Model model,  Authentication auth,
            @RequestParam(value="user",required = false, defaultValue = "") String email){
		
		User user;
        if(!email.equals("")){
             user = userImpl.getByEmail(email);
        }else{
            String Email = auth.getName();
            user = userImpl.getByEmail(Email);
        }
		
        model.addAttribute("restaurants", restaurantService.getAllByUser(user));
		model.addAttribute("user", user);
		
        return "owner/index";
    }
	
	@GetMapping(value="/addRestaurant")
	public String addRestaurantView( Model model ) {
		model.addAttribute("restaurantDto",new RestaurantDto());
		model.addAttribute("zones", zoneService.getAllZones());
		model.addAttribute("specialities", specialiteService.getAll());
		model.addAttribute("villes", villeService.getAll());
		model.addAttribute("series", serieService.getAll());
		
        
		return "owner/addRestaurant";
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
	
	@RequestMapping(value="/addRestaurant", method=RequestMethod.POST)
	public String addRestaurant(@RequestParam("photos") MultipartFile[] photos,
    		@RequestParam("nom") String nom,
    		@RequestParam("adresse") String adresse,
    		@RequestParam("zone") Long zone,
    		@RequestParam("serie") Long serie,
    		@RequestParam("lat") double lat,
    		@RequestParam("longs") double longs,
    		@RequestParam("heure_op") String ouvre,
    		@RequestParam("heure_cl") String ferme,
    		@RequestParam("week") Boolean week,
    		@RequestParam("speciality") Long speciality,
    		@RequestParam(value="user",required = false, defaultValue = "") String email,
    		Model model, Authentication auth) {
		
		User user;
        if(!email.equals("")){
             user = userImpl.getByEmail(email);
        }else{
            String Email = auth.getName();
            user = userImpl.getByEmail(Email);
        }
        
        System.out.println(week);
		
        restaurantService.addRestaurant(photos,nom,adresse,zone,serie,lat,longs,ouvre,ferme,week,speciality,user);

		return "redirect:/owner/index";
	}
	
	@GetMapping("/deleterestaurant/{id}")
	public String deleteRestaurant(@PathVariable("id") Long id) {
		restaurantService.delete(id);
		return "redirect:/owner/index";
	}
}
