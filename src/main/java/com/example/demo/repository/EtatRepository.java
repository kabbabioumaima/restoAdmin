package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Etat;

@Repository
public interface EtatRepository extends JpaRepository<Etat, Long>{
	
	Etat findByName(String name);

}