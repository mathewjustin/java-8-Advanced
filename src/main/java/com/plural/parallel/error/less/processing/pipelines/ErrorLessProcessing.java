package com.plural.parallel.error.less.processing.pipelines;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*main agenda is to explore optional patttern
 * 
 * Advance use of optional
 * 
 * */
public class ErrorLessProcessing {
	public static void main(String[] args) {

		List<Person>persons=new ArrayList<>();
		persons.add(new Person("Jusitn", 27));
		persons.add(new Person("Praveen", 30));
		persons.add(new Person("Karthik", 29));
		persons.add(new Person("Thomas", 44));
		
		
		int max=persons.stream()
				.map(p->p.getAge())
				.reduce(0,Integer::max);
//		0 is the identity , because there wont be any minus values in ages
		
//		Suppose if we need to compuet an average
//		this time average method has no identity element
//		so we dont know what is the value of the method if computed on an empty stream

		OptionalDouble average= persons.stream().mapToInt(e->e.getAge())
		.average();
		//When we are not sure weather there is a return value then it will be wrapped on optional.
		//Optional can be seen as a wrapper type, difference is an optional can be empty 
		//There can be no value in an optional
		
//		isPresent=> to check weather a value is there .get=>to get value
		
//		There is a varient orelse and default value These are some smarter ways
		average.orElse(1);
		average.orElseGet(()->new Person().getDefault());
		
		
		Optional<Double> optional=NewMath.inv(3.4);
		
		//? leverage optional to stream, We can use method reference it will become much cleaner
		Stream<Double> doubelStreams=NewMath.inv(3.4).//Optional<Double> if 
		flatMap(d->NewMath.sqrt(d))//Optional<Double>
		.map(d->Stream.of(d))//Optional<Stream<Double>>
		.orElseGet(()->Stream.empty());////Stream<Double>
		
	  
		List<Double>result=new ArrayList<>();
		//parallel() will go wrong here and it will create a race conditions
	/*	ThreadLocalRandom.current()
		.doubles(10000).boxed()
		.forEach(doub->NewMath.inv(doub)
				.ifPresent(inv->NewMath.sqrt(inv).
						ifPresent(sqts->result.add(sqts)))
				);*/
		
//In the following pattern we can make use of parallel
		        Function<Double, Stream<Double>>flatMapper=
				d->NewMath.inv(d)
				.flatMap(inv->NewMath.sqrt(inv))
				.map(sqrs->Stream.of(sqrs))
				.orElseGet(()->Stream.empty());
				
				result=ThreadLocalRandom.current()
				.doubles(10000).boxed().parallel()
//				.map(d->d*20-10) to modify the stream, we can see some datas are discarded
				.flatMap(flatMapper)
				.collect(Collectors.toList());
				
		System.out.println(result.size());
		
		
		
		
	}

	
}


class NewMath{
	public static Optional<Double>sqrt(Double d)
	{
		return d>0d?Optional.of(Math.sqrt(d)):Optional.empty();
	}
	
	public static Optional<Double>inv(Double d)
	{
		return d!=0d?Optional.of(1d/d):Optional.empty();
	}
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
	public Double getDefault()
	{
		return 0.0;
	}
	private String name;
	private int age;
}