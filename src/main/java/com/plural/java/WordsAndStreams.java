package com.plural.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Merge 3 files
//convert into words
//filter all the words whose length is greater than 4
public class WordsAndStreams {
	public static void main(String[] args) {

		Path linesFile1 = Paths.get("./src/main/resources/linesFile1.txt");
		Path linesFile2 = Paths.get("./src/main/resources/linesFile2.txt");
		List<String>wordsWithLengthGreaterThan4=new ArrayList<>(); 
		Function<String,Stream<String>> splitToWords=lines->Stream.of(lines.split(" "));
		try(Stream<String>file1Lines=Files.lines(linesFile1);Stream<String>file2Lines=Files.lines(linesFile2))
		{
			
			Stream<Stream<String>> mergedLines=Stream.of(file1Lines,file2Lines);
			
			wordsWithLengthGreaterThan4=mergedLines.flatMap(Function.identity())
			.flatMap(splitToWords)
			.filter(e->e.length()>4)
			.collect(Collectors.toList());
			
		}catch(Exception e)
		{
			
		}
		
	}
}
