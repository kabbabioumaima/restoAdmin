package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Serie;
import com.example.demo.model.Ville;
import com.example.demo.repository.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private SerieRepository serieRepository;
	
	public List<Serie> getAll() {
		return serieRepository.findAll();
	}
	
	public Serie save(String nom) {
		Serie serie = new Serie(nom);
		return serieRepository.save(serie);
	}
	
	public Serie edit(Long id,String nom) {
		Serie serie = serieRepository.getById(id);
		serie.setNom(nom);
		return serieRepository.save(serie);
	}
	
	public void delete(Long id) {
		Serie ville = serieRepository.getById(id);
		serieRepository.delete(ville);
	}
	
	public long count() {
		return serieRepository.count();
	}
}
