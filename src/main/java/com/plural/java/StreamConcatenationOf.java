package com.plural.java;

import static org.assertj.core.api.Assertions.catchThrowable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamConcatenationOf {
	public static void main(String[] args) {

		Path path1 = Paths.get("./src/main/resources/people.txt");

		Path path2 = Paths.get("./src/main/resources/people2.txt");
		
		Path path3 = Paths.get("./src/main/resources/people3.txt");

		try (Stream<String> stream_1 = Files.lines(path1); Stream<String> stream_2 = Files.lines(path2);
				Stream<String> stream_3 = Files.lines(path3)	) {
			Stream<Stream<String>> streamOfTwo=Stream.of(stream_1, stream_2);
			//Can leads to stacck overflow. Better solution is Stream.Of(___)
			Stream<Stream<String>> streamOfThree=Stream.of(stream_1, stream_2, stream_3);
			
			//Here its stream of stream, we need to flatenize this
			Stream<String> flattenedStream=streamOfThree.flatMap(Function.identity());
			//Here flattenedStream will contains all the lines from the concatenated Stream of Streams.
			flattenedStream.forEach(System.out::println);
		} catch (Exception e) {
			// TODO: handle exception
		}

//		USE CASE 2 LINES INTO ARRAY OF STREAM OF WORDS

		Path linesFile1 = Paths.get("./src/main/resources/linesFile1.txt");
		Path linesFile2 = Paths.get("./src/main/resources/linesFile2.txt");

		try(Stream<String>stream_1=Files.lines(linesFile1))
		{
			stream_1.forEach(e->{
				
			});
			
			
		}catch(Exception e)
		{
			e.printStackTrace();		
		}
		
	}
		
		
}
