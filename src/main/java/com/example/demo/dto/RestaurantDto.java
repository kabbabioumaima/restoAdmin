package com.example.demo.dto;

import java.io.Serializable;
import java.util.Set;

import com.example.demo.model.Etat;
import com.example.demo.model.Photo;
import com.example.demo.model.Serie;
import com.example.demo.model.Specialite;
import com.example.demo.model.User;
import com.example.demo.model.Zone;

public class RestaurantDto implements Serializable {
	
	private Integer id;
	private String nom;
	private String adresse;
	private Double lat;
	private Double longs;
	private Integer rank;
	private String heure_op;
	private String heure_cl;
	private Boolean week; 
	private Serie serie;
	private Zone zone;
    private Set<Specialite> specialities;
	private Etat etat;
	private Set<Photo> photos;
	private User owner;
	
	public RestaurantDto() {
		super();
	}

	public RestaurantDto(Integer id, String nom, String adresse, Double lat, Double longs, Integer rank, String heure_op,
			String heure_cl, Boolean week, Serie serie, Zone zone, Set<Specialite> specialities, Etat etat,
			Set<Photo> photos, User owner) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.lat = lat;
		this.longs = longs;
		this.rank = rank;
		this.heure_op = heure_op;
		this.heure_cl = heure_cl;
		this.week = week;
		this.serie = serie;
		this.zone = zone;
		this.specialities = specialities;
		this.etat = etat;
		this.photos = photos;
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLongs() {
		return longs;
	}

	public void setLongs(Double longs) {
		this.longs = longs;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getHeure_op() {
		return heure_op;
	}

	public void setHeure_op(String heure_op) {
		this.heure_op = heure_op;
	}

	public String getHeure_cl() {
		return heure_cl;
	}

	public void setHeure_cl(String heure_cl) {
		this.heure_cl = heure_cl;
	}

	public Boolean isWeek() {
		return week;
	}

	public void setWeek(Boolean week) {
		this.week = week;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public Set<Specialite> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Set<Specialite> specialities) {
		this.specialities = specialities;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	@Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nom = " + nom + ", " +
                "adresse = " + adresse + ", " +
                "zone = " + zone + ", " ;
    }
}
