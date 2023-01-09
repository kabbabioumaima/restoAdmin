package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Ville;
import com.example.demo.model.Zone;

public interface VilleRepository extends JpaRepository<Ville, Long>{
	Ville getById(Long id);
}
