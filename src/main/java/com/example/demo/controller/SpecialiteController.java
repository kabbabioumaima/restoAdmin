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

import com.example.demo.model.Specialite;
import com.example.demo.repository.SpecialiteRepository;
import com.example.demo.services.SpecialiteService;

@Controller
@RequestMapping("/admin/")
public class SpecialiteController {
	@Autowired
	private SpecialiteService specialiteService;
	
	@PostMapping("/saveSpecialite")
	public String save(@RequestParam("nom") String nom,Model model){
		specialiteService.save(nom);
		return "redirect:/admin/specialite";
	}

	@PostMapping("/editSpecialite/{id}")
	public String edit(@PathVariable(name = "id") Long id,
						@RequestParam("nom") String nom,Model model){
		specialiteService.edit(id,nom);
		return "redirect:/admin/specialite";
	}
	
	@GetMapping("/deleteSpecialite/{id}")
	public String delete(@PathVariable(name = "id") Long id){
		specialiteService.delete(id);
		return "redirect:/admin/specialite";
	}
	
	
	
}
