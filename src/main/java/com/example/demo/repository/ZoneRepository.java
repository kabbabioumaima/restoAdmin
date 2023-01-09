package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Ville;
import com.example.demo.model.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Long>{
	Zone getById(Long id);
	List<Zone> findAllByVille(Ville ville);

}
