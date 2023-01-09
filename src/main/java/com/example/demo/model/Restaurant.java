package com.example.demo.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="restaurant")
public class Restaurant {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@NotEmpty
	private String adresse;
	private Double lat;
	private Double longs;
	private Integer rank;
	@NotEmpty
	private String heure_op;
	@NotEmpty
	private String heure_cl;
	private Boolean week; 
	@ManyToOne
	@JoinColumn(name="serie_id", nullable=false)
	@JsonBackReference
	private Serie serie;
	@ManyToOne
	@JoinColumn(name="zone_id", nullable=false)
	@JsonBackReference
	private Zone zone;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "speciality_id", nullable=false)
	@JsonManagedReference
    private Specialite speciality;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="etat_id", nullable=false)
	@JsonManagedReference
	private Etat etat;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="restaurant")
	@JsonBackReference
	private Set<Photo> photos;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="owner_id",referencedColumnName = "id")
	@JsonManagedReference
	private User owner;
	
	public Restaurant(@NotEmpty String nom, @NotEmpty String adresse, @NotEmpty Double lat, @NotEmpty Double longs,
			@NotEmpty @NotEmpty String heure_op, @NotEmpty @NotEmpty String heure_cl,Etat etat, Boolean week, Serie serie, Zone zone,
			Specialite speciality, Set<Photo> photos, User owner) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.lat = lat;
		this.longs = longs;
		this.heure_op = heure_op;
		this.heure_cl = heure_cl;
		this.week = week;
		this.serie = serie;
		this.zone = zone;
		this.speciality = speciality;
		this.photos = photos;
		this.etat = etat;
		this.owner = owner;
	}
	
	public Restaurant() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", lat=" + lat + ", longs=" + longs
				+ ", rank=" + rank + ", heure_op=" + heure_op + ", heure_cl=" + heure_cl + ", week=" + week + ", serie="
				+ serie + ", zone=" + zone + "]";
	}
	
	public Specialite getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Specialite speciality) {
		this.speciality = speciality;
	}

	public Boolean getWeek() {
		return week;
	}

	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

}
