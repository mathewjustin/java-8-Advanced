package com.plural.parallel.data.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class ParallelDataProcessing {
	// Paralll streams in action
	public static void main(String[] args) {

		// Stream.iterate("+", s->s + "+")
		// .limit(6)
		// .forEach(System.out::println);

		// More threads will be there
		// Stream.iterate("+", s->s + "+")
		// .parallel()
		// .limit(6)
		// .peek(s->System.out.println(s+"Processed in
		// "+Thread.currentThread().getName()))
		// .forEach(System.out::println);

		// Now we can limit the size of fork join to 2

		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2"); // With this we can control
		// the worked threads in the fork join pool, in this case only 2 workers will be
		// allocated for the following
		// stream operations

		/*
		 * Stream.iterate("+", s->s + "+") .parallel() .limit(6)
		 * .peek(s->System.out.println(s+"Processed in "+Thread.currentThread().getName(
		 * ))) .forEach(System.out::println);
		 */

		/* Add them to a list */
		List<String> strings = new ArrayList<>();

		// .parallel() will make th eprocessing parallel, and strings.add() will be
		// called
		// from multiple threads and it will throw exception}}Race condition.
		/*
		 * Stream.iterate("+", s -> s + "+").parallel().limit(1000).forEach(e ->
		 * strings.add(e)); System.out.println("# " + strings.size());
		 */
		// CUNCURRENT ARRAYLIST WILL FIX THE ISSUE BUT TERRIBLE PERFORMANCE
		List<String> concurrentAware = new CopyOnWriteArrayList();

		Stream.iterate("+", s -> s + "+").parallel().limit(1000).forEach(e -> concurrentAware.add(e));
		System.out.println("# " + concurrentAware.size());

		// THE ABOVE CODE IS NOT THERIGHT ONE, ALWAYS USE COLLECTORS, IT WILL BE THREAD
		// SAFE AND EFFICIENT.
		// COLLECTOR PATTERN IS THE RIGHT ONE TO USE IN THIS SCENERIOS
		

	}
}
