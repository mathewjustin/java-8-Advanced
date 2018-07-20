package com.plural.rank.student;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//2 files 
//1 containing set of 4 lines
//id number
//first name
//last name
//dept

//2nd file containing 2 lines
//id number
//rank

//read both files usign spliterator 
//add sort based on rank
//display names based on rank in ascending order.
public class RankStudent {
	public static void main(String[] args) {

		Path studentData = Paths.get("./src/main/resources/rankstudent_student_data");
		Path studentsRankData = Paths.get("./src/main/resources/rankstudent_student_ranks");

		try (Stream<String> studentDataLines = Files.lines(studentData);
				Stream<String> studentRankLines = Files.lines(studentsRankData)) {

			Spliterator<String> studentDataLinesSpliterator = studentDataLines.spliterator();
			Spliterator<Student> studentSpliterator = new StudentDataSpliterator(studentDataLinesSpliterator);
			Stream<Student> studentStream = StreamSupport.stream(studentSpliterator, false);

			studentDataLinesSpliterator = studentRankLines.spliterator();
			Spliterator<RankData> rankDataSpliterator = new RankSpliterator(studentDataLinesSpliterator);
			Stream<RankData> rankDataStream = StreamSupport.stream(rankDataSpliterator, false);

			// both streams are ready
			Map<String, RankData> rnkMap = rankDataStream
					.collect(Collectors.toMap(RankData::getIdNumber, Function.identity(), (p1, p2) -> p1));

			// Pushing rank to student
			List<Student> studentList = studentStream.map(e -> {
				e.setRankData(rnkMap.get(e.getIdNumber()));
				return e;
			}).collect(Collectors.toList());

			studentList.sort(new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					// TODO Auto-generated method stub
					return Integer.compare(o1.getRankData().getRank(), o2.getRankData().getRank());
				}
			});
			;
			studentList.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
class Student {
	private String firstName;
	private String lastName;
	private String idNumber;
	private String dept;
	private RankData rankData;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
class RankData {
	private String idNumber;
	private Integer rank;
}