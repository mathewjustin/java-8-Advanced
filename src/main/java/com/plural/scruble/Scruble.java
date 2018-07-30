package com.plural.scruble;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Scruble {
	public static void main(String[] args) throws IOException {
		Path path1 = Paths.get("./src/main/resources/ospd.txt");
		Path path2 = Paths.get("./src/main/resources/words.shakespeare.txt");

		Set<String> scruble = Files.lines(path1).map(word -> word.toLowerCase()).collect(Collectors.toSet());
		Set<String> shakespeare = Files.lines(path2).map(word -> word.toLowerCase()).collect(Collectors.toSet());

		System.err.println("# Words in scruble:" + scruble.size());
		System.err.println("# Words in shakespeare:" + shakespeare.size());

		final int[] scrabbleENScore = {
				// a, b, c, d, e, f,  g, h, i ,j, k, l, m, n, o, p, q, r ,s, t, u, v, w, x  ,y, z
				   9, 2, 2, 1, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6 , 4, 2, 2, 1, 2, 1};

		Function<String, Integer> score = word -> word.chars().map(letter -> {
			// This is s spacial logic to find out the score of a letter passed
			// eg. score of c, to get the index where the score is stored, ascii value of
			// c-ascii of a=2=>
			// scrabbleENScore[2]=>8
			return scrabbleENScore[letter - 'a'];
		}).sum();

		// Another handy implmentation
		ToIntFunction<String> intScore = word -> word.chars().map(letter -> scrabbleENScore[letter - 'a']).sum();

		// Best Word?;

		String bestWord = shakespeare.stream().max(Comparator.comparing(score)).get();
		System.err.println("The best Word=" + bestWord);

		// Avoid all the words which is not there in the scrubble dictionary
		String bestWordPresentInBoth = shakespeare.stream().filter(e -> scruble.contains(e))
				.max(Comparator.comparing(score)).get();
		System.err.println("The best Word=" + bestWordPresentInBoth);

		// Find the statics

		IntSummaryStatistics intStatitics = shakespeare.stream().filter(scruble::contains).mapToInt(intScore)
				.summaryStatistics();
		System.err.println("Statitics=" + intStatitics);

		shakespeare = Files.lines(path2).map(word -> word.toLowerCase()).collect(Collectors.toSet());

		Map<Integer, List<String>> histoGramByWords = shakespeare.stream()
				.filter(scruble::contains)
				.collect(Collectors.groupingBy(score));
		
		System.out.println("# of keys " + histoGramByWords.size());

		
		//Getting best 3 key val pair
		histoGramByWords.entrySet().stream()
		.sorted(Comparator.comparing(entry->-entry.getKey())).
			limit(3).forEach(entry->
			System.out.println(entry.getKey()+"-"+entry.getValue())
				);;
		
	}
}
