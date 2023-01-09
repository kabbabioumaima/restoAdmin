package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ville;
import com.example.demo.repository.VilleRepository;
import com.example.demo.services.VilleService;


@Controller
@RequestMapping("/admin/")
public class VilleController {
	@Autowired
	private VilleService villeService;
	
	@PostMapping("/saveVille")
	public String save(@RequestParam("nom") String nom,Model model){
        model.addAttribute("addSucc","Ville ajoutée avec succés");
        model.addAttribute("saveFailed","Ajou échoué");
		villeService.saveVille(nom);
		return "redirect:/admin/ville";
	}
	
	@PostMapping("/editVille/{id}")
	public String edit(@PathVariable(name = "id") Long id,
						@RequestParam("nom") String nom,Model model){
		villeService.editVille(id,nom);
		return "redirect:/admin/ville";
	}
	
	@GetMapping("/deleteVille/{id}")
	public String delete(@PathVariable(name = "id") Long id){
		villeService.deleteVille(id);
		return "redirect:/admin/ville";
	}
	
	/*
	 * @GetMapping(value = "/count") public long countStudent() { return
	 * villeRepository.count(); }
	 */
	
	
}
