package com.plural.custom.collectors;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActorsAndMovies {
	public static void main(String[] args) throws IOException {

		// # of actors
		Set<Movie> movies = new HashSet<>();

		Stream<String> lines = Files.lines(

				Paths.get("./src/main/resources/movies-mpaa.txt"), Charset.forName("windows-1252"));

		lines.forEach((String line) -> {
			String[] elements = line.split("/");
			String title = elements[0].substring(0, elements[0].toString().lastIndexOf("(")).trim();
			String releaseYear = elements[0].substring(elements[0].toString().lastIndexOf("(") + 1,
					elements[0].toString().lastIndexOf(")"));

			if (releaseYear.contains(",")) {
				// Movies with a coma in their title are discarded
				return;
			}

			Movie movie = new Movie(title, Integer.valueOf(releaseYear));

			for (int i = 1; i < elements.length; i++) {
				String[] name = elements[i].split(", ");
				String lastName = name[0].trim();
				String firstName = "";
				if (name.length > 1) {
					firstName = name[1].trim();
				}

				Actor actor = new Actor(lastName, firstName);
				movie.addActor(actor);
			}

			movies.add(movie);
		});
		
		System.out.println("# of movies "+movies.size());
		
		// # of actors
		long numberOfActors=movies.stream()
						.flatMap(e->e.getActors().stream()) //Stream<Actors>
						.distinct()
						.count();
		System.out.println("# of actors "+numberOfActors);
		
		//#of actors who has played in the greatest # of movies
		Map.Entry<Actor, Long> get=
	    movies.stream()
		.flatMap(e->e.getActors().stream())
		.collect(Collectors.groupingBy(
		     actor->actor,Collectors.counting()
		)).entrySet().stream()
		    .max(Map.Entry.comparingByValue())
		.get();
		
		System.out.println("Most viewed actor is "+get.getKey()+"-"+get.getValue());
		
		//Actors who has played gretest # of movies during a year
		
		//Map<releaseYears,Map<Actor,# of movies during that year>>
		
		
		
		
	}
}
