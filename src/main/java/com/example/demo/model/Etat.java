package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="etat")
public class Etat {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@NotEmpty
	private String name;// PENDING, VALIDATED, NOT_VALIDATED
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="etat")
	@JsonBackReference
	private Set<Restaurant> restaurant;
	public Etat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Etat(@NotEmpty String name, Set<Restaurant> restaurant) {
		super();
		this.name = name;
		this.restaurant = restaurant;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Restaurant> getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Set<Restaurant> restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
