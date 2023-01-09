package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ville;
import com.example.demo.repository.VilleRepository;

@Service
public class VilleService {

	@Autowired
	private VilleRepository villeRepository;
	
	public List<Ville> getAll(){
		return villeRepository.findAll();
	}
	
	public Ville saveVille(String nom) {
		Ville ville = new Ville(nom);
		return villeRepository.save(ville);
	}
	
	public Ville editVille(Long id,String nom) {
		Ville ville = villeRepository.getById(id);
		ville.setNom(nom);
		return villeRepository.save(ville);
	}
	
	public void deleteVille(Long id) {
		Ville ville = villeRepository.getById(id);
		villeRepository.delete(ville);
	}
	
	public long count() {
		return villeRepository.count();
	}
}
