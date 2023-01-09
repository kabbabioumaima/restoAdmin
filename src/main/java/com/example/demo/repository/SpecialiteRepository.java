package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Specialite;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long>{
	Specialite getById(Long id);
	public Specialite findByNom(String nom);

}
