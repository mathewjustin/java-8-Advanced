# java-8-Advanced based on Advance java courses from pluralsight.

1.Creating custom spliterator on non conventional data sources.

2.Nio file operations with streams, multi file merging and flatMapping to single streams and more.!

3. In package com.plural.rank.student it has an interesting application of spliterator
USE CASE IS AS FOLLOWS.

2 files First one containing set of 4 lines
id number
first name
last name
dept
file name:rankstudent_student_data

2nd file containing 2 lines
id number
rank
file name:rankstudent_student_ranks

read both files using spliterator 
add sort based on rank
display list based on rank in ascending order.