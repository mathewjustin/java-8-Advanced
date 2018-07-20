package com.plural.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//file 1 content 
//1
//2
//3
//4
//file 2 content
//1
//2
//3
//4

//outputfile
//2
//4
//6
//8

//read from these files and add them then write output to a file

public class WordsAndStreams2 {
	public static void main(String[] args) {
		
		Path path1=Paths.get("./src/main/resources/wordsandstreams_1");
		Path path2=Paths.get("./src/main/resources/wordsandstreams_2");
		
 		try(Stream<String>file1stream=Files.lines(path1);Stream<String>file2stream=Files.lines(path2))
		{
			
			List<Integer> file1=file1stream.map(e->Integer.parseInt(e)).collect(Collectors.toList());
            List<Integer> file2=file2stream.map(e->Integer.parseInt(e)).collect(Collectors.toList());

			List<Integer> totalOfBoth= IntStream.range(0,file1.size()).
					mapToObj(i->file1.get(i)+file2.get(i)).
					collect(Collectors.toList());
 			Files.write(Paths.get("./src/main/resources/wordsandstreams2output"),totalOfBoth.toString().getBytes());
		}catch(Exception e)
		{
			
		}
		 
	
	}
	
	 
	}

