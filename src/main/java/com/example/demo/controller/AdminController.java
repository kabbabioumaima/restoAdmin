package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Photo;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.EtatRepository;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.services.PhotoService;
import com.example.demo.services.RestaurantService;
import com.example.demo.services.SerieService;
import com.example.demo.services.SpecialiteService;
import com.example.demo.services.VilleService;
import com.example.demo.services.ZoneService;
import com.example.demo.utils.EtatEnum;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private VilleService villeService;
	
	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private SerieService serieService;
	@Autowired
	private SpecialiteService specialiteService;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private EtatRepository etatRepository;
	@Autowired
	private PhotoService photoService;
	
	@GetMapping(value="/index")
    public String  adminView(Model model){
		model.addAttribute("nZones", zoneService.count());
		model.addAttribute("nVilles", villeService.count());
		model.addAttribute("nRestaus", restaurantService.count());
		model.addAttribute("nSeries", serieService.count());
        return "admin/index";
    }
	
	@GetMapping(value="/restaurant")
    public String  restaurantView(Model model){
		model.addAttribute("restaurants", restaurantService.getAll());
        return "admin/restaurant";
    }
	
	@GetMapping(value="/ville")
    public String  villeView(Model model){
		model.addAttribute("villes", villeService.getAll());
        return "admin/ville";
    }
	
	@GetMapping(value="/statistics")
    public String  statisticView(Model model){
		model.addAttribute("totalByVilleList", restaurantRepository.countTotalByVille());
		model.addAttribute("totalByZoneList", restaurantRepository.countTotalByZone());
        return "admin/statistiques";
    }
	
	@GetMapping(value="/zone")
    public String  zoneView(Model model){
		model.addAttribute("villes", villeService.getAll());
		model.addAttribute("zones", zoneService.getAllZones());
        return "admin/zone";
    }
	
	@GetMapping(value="/serie")
    public String  serieView(Model model){
		model.addAttribute("series", serieService.getAll());
        return "admin/serie";
    }
	
	@GetMapping(value="/specialite")
    public String  specialiteView(Model model){
		model.addAttribute("sepecialities", specialiteService.getAll());
        return "admin/specialite";
    }
	
	@GetMapping(value="/validateRestaurant/{id}")
    public String validate(@PathVariable(name="id")Long id){
		Restaurant r = restaurantRepository.getById(id);
		System.out.println(r.getNom());
		r.setEtat(etatRepository.findByName(EtatEnum.VALIDATED.name()));
		restaurantRepository.save(r);
        return "redirect:/admin/restaurant";
    }
	
	@GetMapping(value="/unvalidateRestaurant/{id}")
    public String unvalidate(@PathVariable(name="id")Long id){
		Restaurant r = restaurantRepository.getById(id);
		System.out.println(r.getNom());
		r.setEtat(etatRepository.findByName(EtatEnum.NOT_VALIDATED.name()));
		restaurantRepository.save(r);
		return "redirect:/admin/restaurant";
    }
	
	
	@GetMapping(value="/deleteRestaurant/{id}")
    public String delete(@PathVariable(name="id")Long id){
		restaurantService.delete(id);
		return "redirect:/admin/restaurant";
    }
	
}
