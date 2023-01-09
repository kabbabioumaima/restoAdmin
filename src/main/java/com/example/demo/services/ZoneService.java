package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ville;
import com.example.demo.model.Zone;
import com.example.demo.repository.VilleRepository;
import com.example.demo.repository.ZoneRepository;

@Service
public class ZoneService {

	@Autowired
	private ZoneRepository zoneRepository;
	
	@Autowired
	private VilleRepository villeRepository;
	
	public Zone saveZone(String nom, Long id) {
		Ville ville = villeRepository.getById(id);
		Zone zone = new Zone(nom, ville);
		return zoneRepository.save(zone);
	}
	
	public List<Zone> getAllZonesByVille(Long id) {
		Ville ville = villeRepository.getById(id);
		return zoneRepository.findAllByVille(ville);
	}
	
	public List<Zone> getAllZones(){
		return zoneRepository.findAll();
	}
	
	public long count() {
		return zoneRepository.count();
	}
	
	public Zone edit(Long id,String nom,Long idVille) {
		Zone zone = zoneRepository.getById(id);
		zone.setNom(nom);
		zone.setVille(villeRepository.getById(idVille));
		return zoneRepository.save(zone);
	}
	
	public void delete(Long id) {
		Zone zone = zoneRepository.getById(id);
		zoneRepository.delete(zone);
	}
}
