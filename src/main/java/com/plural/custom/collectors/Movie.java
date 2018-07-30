package com.plural.custom.collectors;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Movie {

	public Movie(String title2, Integer valueOf) {
		// TODO Auto-generated constructor stub
		this.title=title2;
		this.releaseYear=valueOf;
	}
	private String title;
	private int releaseYear;
	Set<Actor>actors=new HashSet<>();
	public void addActor(Actor actor) {
		this.actors.add(actor);		
	}
}
