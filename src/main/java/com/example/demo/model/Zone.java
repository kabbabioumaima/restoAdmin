package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="zone")
public class Zone {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@ManyToOne
    @JoinColumn(name="ville_id", nullable=false)
	@JsonBackReference
	private Ville ville;
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="zone")
	@JsonManagedReference
	private Set<Restaurant> restaurants;
	
	public Zone() {
		super();
	}
	public Zone(String nom, Ville ville) {
		super();
		this.nom = nom;
		this.ville = ville;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	@Override
	public String toString() {
		return "Zone [id=" + id + ", nom=" + nom + ", ville=" + ville + "]";
	}
	
	

}
