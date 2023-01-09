package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Specialite;
import com.example.demo.model.Ville;
import com.example.demo.repository.SpecialiteRepository;

@Service
public class SpecialiteService {

	@Autowired
	private SpecialiteRepository repository;
	
	public List<Specialite> getAll() {
		return repository.findAll();
	}
	
	public Specialite save(String nom) {
		Specialite s = new Specialite(nom);
		return repository.save(s);
	}
	
	public Specialite edit(Long id,String nom) {
		Specialite s = repository.getById(id);
		s.setNom(nom);
		return repository.save(s);
	}
	
	public void delete(Long id) {
		Specialite s = repository.getById(id);
		repository.delete(s);
	}
	
	public long count() {
		return repository.count();
	}
}
