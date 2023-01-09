package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Restaurant;
import com.example.demo.model.Serie;
import com.example.demo.model.Specialite;
import com.example.demo.model.User;
import com.example.demo.model.Ville;
import com.example.demo.model.Zone;
import com.example.demo.utils.Count;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	Restaurant getById(Long id);
	List<Restaurant> findByOwner(User user);
	
	@Query("SELECT DISTINCT r FROM Restaurant r, Zone z WHERE r.id = z.id AND z.ville =:ville_id ")
	List<Restaurant> findByVille(@Param("ville_id")Long ville);
	
	@Query("SELECT DISTINCT v.nom as nom, COUNT(r.id) as total FROM Restaurant r, Zone z, Ville v WHERE r.zone = z.id AND z.ville = v.id GROUP BY v.nom")
	List<Count> countTotalByVille();
	
	List<Restaurant> findByZone(Zone zone);
	List<Restaurant> findAllBySerie(Serie serie);
	List<Restaurant> findAllBySpeciality(Specialite spec);
	@Query("SELECT DISTINCT z.nom as nom, COUNT(r.id) as total FROM Restaurant r, Zone z WHERE r.zone = z.id GROUP BY z.nom")
	List<Count> countTotalByZone();
}
