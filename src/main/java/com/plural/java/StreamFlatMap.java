package com.plural.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Combine multiple files into single stream and convert into words
public class StreamFlatMap {
	public static void main(String[] args) {
		Path linesFile1 = Paths.get("./src/main/resources/linesFile1.txt");
		Path linesFile2 = Paths.get("./src/main/resources/linesFile2.txt");
 
		Function<String,Stream<String>>splitIntoWords1=	
				line->Stream.of(line.split(" "));
				
		//More nice way. Pattern is from java regex package		
		Function<String,Stream<String>>splitIntoWords=
				line->Pattern.compile(" ").splitAsStream(line);
				
		try (Stream<String> stream_1 = Files.lines(linesFile1); Stream<String> stream_2 = Files.lines(linesFile2)) {

//			merge all the contents to a stream and convert them to set of words
			Set<String>words=Stream.of(stream_1,stream_2)
					.flatMap(Function.identity())
					.flatMap(splitIntoWords)
					.collect(Collectors.toSet());
			
			words.stream().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
