package com.plural.collectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CollectorsExample {
	public static void main(String[] args) {

		ArrayList<Person>people=new ArrayList<Person>();
		people.add(new Person("Justin",   44));
		people.add(new Person("Jobs", 12));
		people.add(new Person("Terry", 43));
		people.add(new Person("Tomichen", 23));
		people.add(new Person("Sneha", 23));
		people.add(new Person("Riyaz", 32));
		people.add(new Person("David", 12));
		
		Map<Boolean, List<Person>> peopleByAge=people.stream().
				collect(java.util.stream.Collectors.partitioningBy(e->e.getAge()>20));
		System.out.println(peopleByAge);
		//GroupBy
		
		//To an unmodifiable map
	 Map<Object, List<Person>> peopleByAgeMap=
			people.stream()
			.collect(
					Collectors.collectingAndThen(Collectors.groupingBy(p->p.getAge()), 
				    Collections::unmodifiableMap)					
					);
	 System.out.println(peopleByAgeMap);
	
	}

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
	private String name;
	private Integer age;
}
