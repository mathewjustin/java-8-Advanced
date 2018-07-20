package com.plural.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamConcatenation {
	public static void main(String[] args) {

		Path path1 = Paths.get("./src/main/resources/people.txt");

		Path path2 = Paths.get("./src/main/resources/people2.txt");
		
		Path path3 = Paths.get("./src/main/resources/people3.txt");

		try (Stream<String> stream_1 = Files.lines(path1); Stream<String> stream_2 = Files.lines(path2);
				Stream<String> stream_3 = Files.lines(path3)	) {
			Stream<String> streamOfTwo=Stream.concat(stream_1, stream_2);
			//Can leads to stacck overflow. Better solution is Stream.Of(___)
			Stream<String> streamOfThree=Stream.concat(Stream.concat(stream_1, stream_2), stream_3);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
