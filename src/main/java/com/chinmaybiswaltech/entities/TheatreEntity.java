package com.chinmaybiswaltech.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "theatres")
public class TheatreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long theatreId;

	private String name;

	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private CityEntity city;

	@Column(name = "total_screens")
	private int totalScreens;
}
