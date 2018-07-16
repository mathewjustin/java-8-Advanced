package com.plural.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.plural.model.Person;
import com.plural.spliterator.PersonSpliterator;

public class Spliterator {


	public static void main(String[] args) {

		Path path=Paths.get("./src/main/resources/people.txt");
		
		try(Stream<String>lines=Files.lines(path))
		{
			java.util.Spliterator<String> linesSpliterator= lines.spliterator();
			java.util.Spliterator<Person> peopleSpliterator=new PersonSpliterator(linesSpliterator);
			Stream<Person>people=StreamSupport.stream(peopleSpliterator, false);
			people.forEach(System.out::println);
			
		}catch (Exception e) {
		e.printStackTrace();
		}
	}
}
