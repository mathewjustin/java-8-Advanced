package com.plural.scruble;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import ch.qos.logback.core.util.Duration;

public class Scruble {
	public static void main(String[] args) throws IOException {
		Path path1 = Paths.get("./src/main/resources/ospd.txt");
		Path path2 = Paths.get("./src/main/resources/words.shakespeare.txt");

		Set<String> scruble = Files.lines(path1).map(word -> word.toLowerCase()).collect(Collectors.toSet());
		Set<String> shakespeare = Files.lines(path2).map(word -> word.toLowerCase()).collect(Collectors.toSet());

		System.err.println("# Words in scruble:" + scruble.size());
		System.err.println("# Words in shakespeare:" + shakespeare.size());

		final int[] scrabbleENScore = {
				// a,b,c,d,e,f,g,h,i ,j, k, l ,m,n, o, p,q, r ,s, t, u, v, w, x ,y,z
				1, 2, 8, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };

		Function<String, Integer> score = word -> word.chars().map(letter -> {
			// This is s spacial logic to find out the score of a letter passed
			// eg. score of c, to get the index where the score is stored, ascii value of
			// c-ascii of a=2=>
			// scrabbleENScore[2]=>8
			return scrabbleENScore[letter - 'a'];
		}).sum();
		
		//Another handy implmentation
		ToIntFunction<String>intScore=word->word.chars().map(letter->scrabbleENScore[letter-'a']).sum();
		
  	//Best Word?;

		String bestWord=shakespeare.stream()
				.max(Comparator.comparing(score))
				.get();
		System.err.println("The best Word="+bestWord);
		
		//Avoid all the words which is not there in the scrubble dictionary
		String bestWordPresentInBoth=shakespeare.stream()
				.filter(e->scruble.contains(e))
				.max(Comparator.comparing(score))
				.get();
		System.err.println("The best Word="+bestWordPresentInBoth);
		
		
		//Find the statics

		IntSummaryStatistics intStatitics=shakespeare.stream()
				.filter(scruble::contains)
				.mapToInt(intScore)
				.summaryStatistics();
		System.err.println("Statitics="+intStatitics);
	
	}
}
