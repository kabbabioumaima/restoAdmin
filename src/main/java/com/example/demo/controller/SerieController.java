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

import com.example.demo.model.Serie;
import com.example.demo.repository.SerieRepository;
import com.example.demo.services.SerieService;

@Controller
@RequestMapping("/admin/")
public class SerieController {
	@Autowired
	private SerieService serieService;
	
	@PostMapping("/saveSerie")
	public String save(@RequestParam("nom") String nom){
		serieService.save(nom);
		return "redirect:/admin/serie";
	}
	
	@PostMapping("/editSerie/{id}")
	public String edit(@PathVariable(name = "id") Long id,
						@RequestParam("nom") String nom,Model model){
		serieService.edit(id,nom);
		return "redirect:/admin/serie";
	}
	
	@GetMapping("/deleteSerie/{id}")
	public String delete(@PathVariable(name = "id") Long id){
		serieService.delete(id);
		return "redirect:/admin/serie";
	}
	
	

}
