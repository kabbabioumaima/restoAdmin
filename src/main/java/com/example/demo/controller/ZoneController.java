package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.services.ZoneService;


@Controller
@RequestMapping("/admin/")
public class ZoneController {
	@Autowired
	private ZoneService zoneService;
	
	@PostMapping("/saveZone")
	public String save(@RequestParam("nom") String nom,@RequestParam("idVille") Long id){
		zoneService.saveZone(nom,id);
		return "redirect:/admin/zone";
	}
	
	@PostMapping("/editZone/{id}")
	public String edit(@PathVariable(name = "id") Long id,@RequestParam(name = "villeId") Long villeId,
						@RequestParam("nom") String nom,Model model){
		zoneService.edit(id,nom,villeId);
		return "redirect:/admin/zone";
	}
	
	@GetMapping("/deleteZone/{id}")
	public String delete(@PathVariable(name = "id") Long id){
		zoneService.delete(id);
		return "redirect:/admin/zone";
	}
	
}
