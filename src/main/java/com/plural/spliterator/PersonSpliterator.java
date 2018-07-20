package com.plural.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

import com.plural.model.Person;

public class PersonSpliterator implements Spliterator<Person> {

	private final Spliterator<String> lineSpliterator;
	private String name;
	private int age;
	private String city;

	public PersonSpliterator(Spliterator<String> lineSpliterator) {
		super();
		this.lineSpliterator = lineSpliterator;
	}
	
	//
	@Override
	public int characteristics() {
//		A stream has a state these are the bits that defines the states
//		Spliterator.CONCURRENT
//		Spliterator.ORDERED
//		Spliterator.DISTINCT
//		Spliterator.SORTED
//		Spliterator.SIZED ...etc
		
//		for arraylist 
//		return Spliterator.ORDERED|Spliterator.SIZED|Spliterator.SUBSIZED
		
//		for hashset, distinct and other properties to define its charecteristics
		
 		return lineSpliterator.characteristics();
 		
 		
	}

	@Override
	public long estimateSize() {
 		return lineSpliterator.estimateSize()/3;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Person> action) {

		 if(lineSpliterator.tryAdvance(line->this.name=line)&&
		 lineSpliterator.tryAdvance(line->this.age=Integer.parseInt(line))&&
		 lineSpliterator.tryAdvance(line->this.city=line))
		 {
			 action.accept(new Person(name, age, city));
		     return true;
		 }else
		 {
			 return false;
		 }
		 
		
	}

	@Override
	public Spliterator<Person> trySplit() {
		// TODO Auto-generated method stub
		return null; //Not in parallel
	}

}
