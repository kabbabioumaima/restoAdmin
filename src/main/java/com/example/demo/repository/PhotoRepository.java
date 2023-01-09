package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Etat;
import com.example.demo.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{

	List<Photo> findAllByRestaurantId(Long id);

}
