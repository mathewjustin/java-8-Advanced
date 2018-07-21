package com.plural.java;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class StreamOfNumbers {
	public static void main(String[] args) {

		//Average of numbers From intstream
		List<Integer>listOfInteger=new ArrayList<>();
		listOfInteger.add(1);
		listOfInteger.add(2);
		listOfInteger.add(3);
		listOfInteger.add(4);
		listOfInteger.add(5);
		listOfInteger.add(6);
		listOfInteger.add(7);
		System.err.println(listOfInteger.stream()
		.filter(i->i>2)
		.mapToInt(i->i)
		.average());
		
//		There are 3 kind of Stream of Numbers
//		Intstream,LongStream and DoubleStream, we can use maptoint->maptoDouble..etc
		
		IntStream intstream=listOfInteger.stream().mapToInt(i->i);
		OptionalDouble avg=intstream.average();
		int sum=intstream.sum();
		OptionalDouble min=intstream.average();
		OptionalInt max=intstream.max();
		IntSummaryStatistics stats=intstream.summaryStatistics();
		
		
		
	}
}
