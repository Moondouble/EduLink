package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("classroomDTO")
public class ClassroomDTO
{
String classNum;
String teacherNum;
String className;
String classContents;
Date classStartDate;
Date classEndDate;
Integer classWeek;
}
