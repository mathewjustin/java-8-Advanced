package com.plural.rank.student;

import java.util.Spliterator;
import java.util.function.Consumer;

public class StudentDataSpliterator implements Spliterator<Student>{

	private final Spliterator<String> lineSpliterator;
	  
	 public StudentDataSpliterator(Spliterator<String> lineSpliterator) {
		 super();
		this.lineSpliterator=lineSpliterator;
	}
	
	@Override
	public int characteristics() {
 		return lineSpliterator.characteristics();
	}

	@Override
	public long estimateSize() {
 		return lineSpliterator.estimateSize()/4;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Student> action) {
		Student student=new Student();
		if(lineSpliterator.tryAdvance(line->student.setIdNumber(line))&&
		lineSpliterator.tryAdvance(line->student.setFirstName(line))&&
		lineSpliterator.tryAdvance(line->student.setLastName(line))&&
		lineSpliterator.tryAdvance(line->student.setDept(line)))
		{
			action.accept(student);
			return true;
		}
  		return false;
	}

	@Override
	public Spliterator<Student> trySplit() {
		// TODO Auto-generated method stub
		return null;
	}

}
